package mk.finki.ukim.mk.lab1_b.service.domain;

import mk.finki.ukim.mk.lab1_b.model.Accommodation;
import mk.finki.ukim.mk.lab1_b.model.Availability;

import java.util.List;
import java.util.Optional;

public interface AvailabilityDomainService {
    List<Availability> findAll();

    Optional<Availability> save(Availability availability);

    Optional<Availability> findById(Long id);

    Optional<Availability> update(Long id, Availability availability);

    void deleteById(Long id);

    List<Availability> findByAccommodationId(Long accommodationId);
}
