package mk.finki.ukim.mk.lab1_b.service.domain.impl;

import jakarta.persistence.EntityNotFoundException;
import mk.finki.ukim.mk.lab1_b.model.Accommodation;
import mk.finki.ukim.mk.lab1_b.model.Availability;
import mk.finki.ukim.mk.lab1_b.repository.AccommodationRepository;
import mk.finki.ukim.mk.lab1_b.repository.AvailabilityRepository;
import mk.finki.ukim.mk.lab1_b.service.domain.AvailabilityDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityDomainServiceImpl implements AvailabilityDomainService {

    private final AvailabilityRepository availabilityRepository;
    private final AccommodationRepository accommodationRepository;

    public AvailabilityDomainServiceImpl(AvailabilityRepository availabilityRepository, AccommodationRepository accommodationRepository) {
        this.availabilityRepository = availabilityRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public List<Availability> findAll() {
        return availabilityRepository.findAll();
    }

    @Override
    public Optional<Availability> save(Availability availability) {
        Accommodation accommodation = accommodationRepository.findById(availability.getAccommodation().getId())
                .orElseThrow(EntityNotFoundException::new);

        Availability newAvailability = new Availability(
                availability.getReservedFrom(),
                availability.getReservedTo(),
                availability.getPrice(),
                accommodation
        );

        return Optional.of(availabilityRepository.save(newAvailability));
    }

    @Override
    public Optional<Availability> findById(Long id) {
        return availabilityRepository.findById(id);
    }

    @Override
    public Optional<Availability> update(Long id, Availability availability) {
        return availabilityRepository.findById(id)
                .map(existingAvailability -> {
                    if (availability.getReservedFrom() != null) {
                        existingAvailability.setReservedFrom(availability.getReservedFrom());
                    }
                    if (availability.getReservedTo() != null) {
                        existingAvailability.setReservedTo(availability.getReservedTo());
                    }
                    if (availability.getPrice() != null) {
                        existingAvailability.setPrice(availability.getPrice());
                    }
                    if (availability.getAccommodation() != null) {
                        Accommodation accommodation = accommodationRepository.findById(availability.getAccommodation().getId())
                                .orElseThrow(EntityNotFoundException::new);
                        existingAvailability.setAccommodation(accommodation);
                    }
                    return availabilityRepository.save(existingAvailability);
                });
    }

    @Override
    public void deleteById(Long id) {
        availabilityRepository.deleteById(id);
    }

    @Override
    public List<Availability> findByAccommodationId(Long accommodationId) {
        return availabilityRepository.findAvailabilityByAccommodationId(accommodationId);
    }
}
