package mk.finki.ukim.mk.lab1_b.controller;

import mk.finki.ukim.mk.lab1_b.dto.*;
import mk.finki.ukim.mk.lab1_b.model.exceptions.InvalidNumberRoomsException;
import mk.finki.ukim.mk.lab1_b.model.views.AccommodationsPerHostView;
import mk.finki.ukim.mk.lab1_b.repository.AccommodationsPerHostRepository;
import mk.finki.ukim.mk.lab1_b.service.application.AccommodationApplicationService;
import mk.finki.ukim.mk.lab1_b.service.domain.AccommodationDomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodations", description = "Endpoints for managing accommodations and reservations")
public class AccommodationController {

    private final AccommodationApplicationService accommodationService;
    private final AccommodationsPerHostRepository accommodationsPerHostRepository;
    public AccommodationController(AccommodationApplicationService accommodationService, AccommodationsPerHostRepository accommodationsPerHostRepository) {
        this.accommodationService = accommodationService;
        this.accommodationsPerHostRepository = accommodationsPerHostRepository;
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get all accommodations", description = "Returns a list of all available accommodations.")
    public List<DisplayAccommodationDto> getAll() {
        return accommodationService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get accommodation by ID", description = "Retrieves an accommodation using its unique ID.")
    public ResponseEntity<DisplayAccommodationDto> getById(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Create new accommodation", description = "Creates a new accommodation for a given host.")
    public ResponseEntity<DisplayAccommodationDto> create(@RequestBody CreateAccommodationDto accommodation) {
        if (accommodation == null || accommodation.hostUsername() == null) {
            return ResponseEntity.badRequest().build();
        }

        return accommodationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Update accommodation", description = "Updates the accommodation data by ID.")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id,
                                                          @RequestBody CreateAccommodationDto accommodation) {
        if (accommodation == null || accommodation.hostUsername() == null) {
            return ResponseEntity.badRequest().build();
        }

        return accommodationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Delete accommodation", description = "Deletes an accommodation by its ID.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/check-in/{id}")
    @Operation(summary = "Check-in accommodation", description = "Performs check-in for a room in the accommodation.")
    public ResponseEntity<Void> checkIn(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.checkinRoom(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/check-out/{id}")
    @Operation(summary = "Check-out accommodation", description = "Performs check-out for a room in the accommodation.")
    public ResponseEntity<Void> checkOut(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.checkoutRoom(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add-reservation")
    @Operation(summary = "Add reservation", description = "Adds a reservation for a user to a selected accommodation.")
    public ResponseEntity<?> addReservation(@RequestBody CreateReservationDto createReservationDto) {
        try {
            accommodationService.addReservation(createReservationDto);
            return ResponseEntity.ok("Reservation successful");
        } catch (InvalidNumberRoomsException ex) {
            return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
        }
    }

    @PostMapping("/confirm-reservations")
    @Operation(summary = "Confirm reservations", description = "Confirms all reservations for the specified user.")
    public ResponseEntity<Void> confirmReservations(@RequestParam(required = false) String username) {
        if (username != null && !username.trim().isEmpty()) {
            accommodationService.confirmReservations(username);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getReservations")
    @Operation(summary = "Get user reservations", description = "Returns a list of all reservations for the specified user.")
    public ResponseEntity<List<DisplayAccommodationDto>> getReservations(@RequestParam(required = false) String username) {
        if (username != null && !username.trim().isEmpty()) {
            List<DisplayAccommodationDto> reservations = accommodationService.getReservationsForUser(username);
            return ResponseEntity.ok(reservations);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/get-statistic")
    public ResponseEntity<List<CategoryDTO>> getStatistic() {
        return ResponseEntity.ok(accommodationService.getStatistics());
    }

    @GetMapping("/by-host")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get accommodations by host", description = "Returns the number of accommodations by host.")
    public List<AccommodationHostDto> getAccommodationsPerHost() {
        return accommodationsPerHostRepository.findAll().stream()
                .map(e -> new AccommodationHostDto(e.getUsername(), e.getNum_accommodations()))
                .collect(Collectors.toList());
    }
}

