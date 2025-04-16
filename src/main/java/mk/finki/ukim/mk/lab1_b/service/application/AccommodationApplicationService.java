package mk.finki.ukim.mk.lab1_b.service.application;

import mk.finki.ukim.mk.lab1_b.dto.CreateAccommodationDto;
import mk.finki.ukim.mk.lab1_b.dto.CreateReservationDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayAccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();

    Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation);

    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation);

    void deleteById(Long id);

    void checkinRoom(Long accommodationId);

    void checkoutRoom(Long accommodationId);

    void addReservation(CreateReservationDto reservation);

    void confirmReservations(String username);

    List<DisplayAccommodationDto>getReservationsForUser(String username);
}
