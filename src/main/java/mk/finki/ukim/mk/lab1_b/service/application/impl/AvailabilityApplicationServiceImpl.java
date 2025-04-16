package mk.finki.ukim.mk.lab1_b.service.application.impl;

import jakarta.persistence.EntityNotFoundException;
import mk.finki.ukim.mk.lab1_b.dto.CreateAvailabilityDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayAvailabilityDto;
import mk.finki.ukim.mk.lab1_b.model.Accommodation;
import mk.finki.ukim.mk.lab1_b.model.Availability;
import mk.finki.ukim.mk.lab1_b.service.application.AvailabilityApplicationService;
import mk.finki.ukim.mk.lab1_b.service.domain.AccommodationDomainService;
import mk.finki.ukim.mk.lab1_b.service.domain.AvailabilityDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityApplicationServiceImpl implements AvailabilityApplicationService {
    private final AvailabilityDomainService availabilityDomainService;
    private final AccommodationDomainService accommodationDomainService;

    public AvailabilityApplicationServiceImpl(AvailabilityDomainService availabilityDomainService, AccommodationDomainService accommodationDomainService) {
        this.availabilityDomainService = availabilityDomainService;
        this.accommodationDomainService = accommodationDomainService;
    }

    @Override
    public List<DisplayAvailabilityDto> findAll() {
        return availabilityDomainService.findAll().stream().map(DisplayAvailabilityDto::from).toList();
    }

    @Override
    public Optional<DisplayAvailabilityDto> save(CreateAvailabilityDto availabilityDto) {
        Accommodation accommodation = accommodationDomainService.findById(availabilityDto.accommodationId())
                .orElseThrow(() -> new EntityNotFoundException("Accommodation not found"));
        Availability availability = availabilityDto.toAvailability(accommodation);
        return availabilityDomainService.save(availability)
                .map(DisplayAvailabilityDto::from);
    }

    @Override
    public Optional<DisplayAvailabilityDto> findById(Long id) {
        return availabilityDomainService.findById(id).map(DisplayAvailabilityDto::from);
    }

    @Override
    public Optional<DisplayAvailabilityDto> update(Long id, CreateAvailabilityDto availabilityDto) {
        Accommodation accommodation = accommodationDomainService.findById(availabilityDto.accommodationId())
                .orElseThrow(() -> new EntityNotFoundException("Accommodation not found"));
        return availabilityDomainService.update(id, availabilityDto.toAvailability(accommodation))
                .map(DisplayAvailabilityDto::from);
    }

    @Override
    public void deleteById(Long id) {
        availabilityDomainService.deleteById(id);
    }

    @Override
    public List<DisplayAvailabilityDto> findByAccommodationId(Long accommodationId) {
        return availabilityDomainService.findByAccommodationId(accommodationId).stream().map(DisplayAvailabilityDto::from).toList();
    }
}
