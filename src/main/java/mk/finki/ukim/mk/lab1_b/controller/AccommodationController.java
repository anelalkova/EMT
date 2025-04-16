package mk.finki.ukim.mk.lab1_b.controller;

import mk.finki.ukim.mk.lab1_b.dto.CreateAccommodationDto;
import mk.finki.ukim.mk.lab1_b.dto.CreateReservationDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayAccommodationDto;
import mk.finki.ukim.mk.lab1_b.service.application.AccommodationApplicationService;
import mk.finki.ukim.mk.lab1_b.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationService;
    private final UserApplicationService userService;

    public AccommodationController(AccommodationApplicationService accommodationService, UserApplicationService userService) {
        this.accommodationService = accommodationService;
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public List<DisplayAccommodationDto> getAll() {
        return accommodationService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<DisplayAccommodationDto> getById(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
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
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/check-in/{id}")
    public ResponseEntity<Void> checkIn(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.checkinRoom(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/check-out/{id}")
    public ResponseEntity<Void> checkOut(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.checkoutRoom(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add-reservation")
    public ResponseEntity<Void> addReservation(@RequestBody CreateReservationDto createReservationDto) {
        if (createReservationDto != null &&
                createReservationDto.accommodationId() != null &&
                createReservationDto.username() != null) {
            accommodationService.addReservation(createReservationDto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/confirm-reservations")
    public ResponseEntity<Void> confirmReservations(@RequestParam(required = false) String username) {
        if (username != null && !username.trim().isEmpty()) {
            accommodationService.confirmReservations(username);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getReservations")
    public ResponseEntity<List<DisplayAccommodationDto>> getReservations(@RequestParam(required = false) String username) {
        if (username != null && !username.trim().isEmpty()) {
            List<DisplayAccommodationDto> reservations = accommodationService.getReservationsForUser(username);
            return ResponseEntity.ok(reservations);
        }
        return ResponseEntity.badRequest().build();
    }
}
