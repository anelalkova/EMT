package mk.finki.ukim.mk.lab1_b.service.application;

import mk.finki.ukim.mk.lab1_b.dto.CreateAvailabilityDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayAvailabilityDto;
import mk.finki.ukim.mk.lab1_b.model.Availability;

import java.util.List;
import java.util.Optional;

public interface AvailabilityApplicationService {

    List<DisplayAvailabilityDto> findAll();

    Optional<DisplayAvailabilityDto> save(CreateAvailabilityDto availabilityDto);

    Optional<DisplayAvailabilityDto> findById(Long id);

    Optional<DisplayAvailabilityDto> update(Long id, CreateAvailabilityDto availabilityDto);

    void deleteById(Long id);

    List<DisplayAvailabilityDto> findByAccommodationId(Long accommodationId);
}
