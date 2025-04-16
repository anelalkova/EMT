package mk.finki.ukim.mk.lab1_b.controller;

import mk.finki.ukim.mk.lab1_b.dto.CreateAvailabilityDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayAvailabilityDto;
import mk.finki.ukim.mk.lab1_b.service.application.AvailabilityApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityController {
    private final AvailabilityApplicationService availabilityApplicationService;

    public AvailabilityController(AvailabilityApplicationService availabilityApplicationService) {
        this.availabilityApplicationService = availabilityApplicationService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get all availabilities", description = "Fetches a list of all availabilities.")
    public List<DisplayAvailabilityDto> getAll() {
        return availabilityApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get availability by ID", description = "Fetches availability details by the specified ID.")
    public ResponseEntity<DisplayAvailabilityDto> getById(@PathVariable Long id) {
        return availabilityApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Add a new availability", description = "Creates a new availability with the provided details.")
    public ResponseEntity<DisplayAvailabilityDto> create(@RequestBody CreateAvailabilityDto availabilityDto) {
        return availabilityApplicationService.save(availabilityDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Update an existing availability", description = "Updates the availability details by the specified ID.")
    public ResponseEntity<DisplayAvailabilityDto> update(@PathVariable Long id,
                                                         @RequestBody CreateAvailabilityDto availabilityDto) {
        return availabilityApplicationService.update(id, availabilityDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Delete an availability", description = "Deletes the availability record by the specified ID.")
    public void delete(@PathVariable Long id) {
        availabilityApplicationService.deleteById(id);
    }

    @GetMapping("/getByAccommodation/{id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get availabilities by accommodation ID", description = "Fetches availabilities related to the specified accommodation ID.")
    public List<DisplayAvailabilityDto> getByAccommodation(@PathVariable Long id) {
        return availabilityApplicationService.findByAccommodationId(id);
    }
}
