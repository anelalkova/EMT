package mk.finki.ukim.mk.lab1_b.service.application.impl;

import jakarta.persistence.EntityNotFoundException;
import mk.finki.ukim.mk.lab1_b.dto.CreateAvailabilityDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayAvailabilityDto;
import mk.finki.ukim.mk.lab1_b.mappers.AvailabilityMapper;
import mk.finki.ukim.mk.lab1_b.model.Accommodation;
import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.model.Availability;
import mk.finki.ukim.mk.lab1_b.repository.AvailabilityRepository;
import mk.finki.ukim.mk.lab1_b.repository.UserRepository;
import mk.finki.ukim.mk.lab1_b.service.application.AvailabilityApplicationService;
import mk.finki.ukim.mk.lab1_b.service.domain.AccommodationDomainService;
import mk.finki.ukim.mk.lab1_b.service.domain.AvailabilityDomainService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvailabilityApplicationServiceImpl implements AvailabilityApplicationService {
    private final AvailabilityDomainService availabilityDomainService;
    private final AccommodationDomainService accommodationDomainService;
    private final UserRepository userRepository;
    private final AvailabilityRepository availabilityRepository;
    private final AvailabilityMapper availabilityMapper;

    public AvailabilityApplicationServiceImpl(AvailabilityDomainService availabilityDomainService, AccommodationDomainService accommodationDomainService, UserRepository userRepository, AvailabilityRepository availabilityRepository, AvailabilityMapper availabilityMapper) {
        this.availabilityDomainService = availabilityDomainService;
        this.accommodationDomainService = accommodationDomainService;
        this.userRepository = userRepository;
        this.availabilityRepository = availabilityRepository;
        this.availabilityMapper = availabilityMapper;
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

    public List<DisplayAvailabilityDto> findByHostUsername(String username) {
        AppUser host = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return availabilityRepository.findByHostUsername(host.getUsername())
                .stream()
                .map(availabilityMapper::toDto)
                .collect(Collectors.toList());
    }
}
