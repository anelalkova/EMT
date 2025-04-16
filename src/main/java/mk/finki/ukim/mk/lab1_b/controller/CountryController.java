package mk.finki.ukim.mk.lab1_b.controller;

import mk.finki.ukim.mk.lab1_b.dto.CreateCountryDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayCountryDto;
import mk.finki.ukim.mk.lab1_b.service.application.CountryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryApplicationService countryService;

    public CountryController(CountryApplicationService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    @Operation(summary = "Get all countries", description = "Fetches a list of all countries.")
    public List<DisplayCountryDto> getAllCountries() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get country by ID", description = "Fetches a country by its ID.")
    public ResponseEntity<DisplayCountryDto> getCountry(@PathVariable Long id) {
        return countryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add new country", description = "Adds a new country based on provided details.")
    public ResponseEntity<DisplayCountryDto> addCountry(@RequestBody CreateCountryDto country) {
        return countryService.save(country)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update country", description = "Updates the country details for the specified ID.")
    public ResponseEntity<DisplayCountryDto> updateCountry(@PathVariable Long id, @RequestBody CreateCountryDto country) {
        return countryService.update(id, country)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete country", description = "Deletes the country with the specified ID.")
    public ResponseEntity<Object> deleteCountry(@PathVariable Long id) {
        if (countryService.findById(id).isPresent()) {
            countryService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
