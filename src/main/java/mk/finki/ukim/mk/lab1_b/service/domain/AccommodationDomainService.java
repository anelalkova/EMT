package mk.finki.ukim.mk.lab1_b.service.domain;

import mk.finki.ukim.mk.lab1_b.model.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationDomainService {
    List<Accommodation> findAll();

    Optional<Accommodation> save(Accommodation accommodation);

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    void deleteById(Long id);

    void checkinRoom(Long accommodationId);

    void checkoutRoom(Long accommodationId);

    void addReservation(String username, Long accommodationId);

    void confirmReservations(String username);

    List<Accommodation> getReservationsForUser(String username);
}
