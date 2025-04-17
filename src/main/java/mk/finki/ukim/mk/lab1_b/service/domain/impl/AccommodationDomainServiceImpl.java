package mk.finki.ukim.mk.lab1_b.service.domain.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab1_b.model.Accommodation;
import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.model.exceptions.InvalidNumberRoomsException;
import mk.finki.ukim.mk.lab1_b.model.exceptions.NoRoomsAvailableException;
import mk.finki.ukim.mk.lab1_b.model.exceptions.NoSuchUserException;
import mk.finki.ukim.mk.lab1_b.repository.AccommodationRepository;
import mk.finki.ukim.mk.lab1_b.repository.UserRepository;
import mk.finki.ukim.mk.lab1_b.service.domain.AccommodationDomainService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationDomainServiceImpl implements AccommodationDomainService {
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;

    public AccommodationDomainServiceImpl(AccommodationRepository accommodationRepository, UserRepository userRepository) {
        this.accommodationRepository = accommodationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if (accommodation.getHost() != null &&
                userRepository.findById(accommodation.getHost().getId()).isPresent()) {
            return Optional.of(
                    accommodationRepository.save(new Accommodation(
                            accommodation.getName(), accommodation.getCategory(),
                            userRepository.findById(accommodation.getHost().getId()).get(),
                            accommodation.getNumRooms()
                    )));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id)
                .map(existingAccommodation -> {
                    if (accommodation.getName() != null) {
                        existingAccommodation.setName(accommodation.getName());
                    }
                    if (accommodation.getCategory() != null) {
                        existingAccommodation.setCategory(accommodation.getCategory());
                    }
                    if (accommodation.getNumRooms() != null) {
                        existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    }
                    if (accommodation.getHost() != null && userRepository.findById(accommodation.getHost().getId()).isPresent()) {
                        existingAccommodation.setHost(userRepository.findById(accommodation.getHost().getId()).get());
                    }
                    return accommodationRepository.save(existingAccommodation);
                });
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void checkinRoom(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Accommodation not found"));

        if (accommodation.getNumAvailableRooms() <= 0) {
            throw new IllegalStateException("No available rooms for check-in.");
        }

        accommodation.setNumAvailableRooms(accommodation.getNumAvailableRooms() - 1);
        accommodationRepository.save(accommodation);
    }

    @Override
    @Transactional
    public void checkoutRoom(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Accommodation not found"));

        if (accommodation.getNumAvailableRooms() >= accommodation.getNumRooms()) {
            throw new InvalidNumberRoomsException();
        }

        accommodation.setNumAvailableRooms(accommodation.getNumAvailableRooms() + 1);
        accommodationRepository.save(accommodation);
    }

    @Override
    public void addReservation(String username, Long accommodationId) {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new RuntimeException("Accommodation not found"));
        if(accommodation.getNumAvailableRooms() > 0) {
            user.getReservations().add(accommodation);
            userRepository.save(user);
        }else throw new NoRoomsAvailableException();
    }

    @Override
    public void confirmReservations(String username) {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Accommodation> reservations = user.getReservations();
        for (Accommodation accommodation : reservations) {
            if (accommodation.getNumAvailableRooms() > 0) {
                checkinRoom(accommodation.getId());
            } else {
                throw new NoRoomsAvailableException();
            }
        }

        user.setReservations(new ArrayList<>());
        userRepository.save(user);
    }

    @Override
    public List<Accommodation> getReservationsForUser(String username) {
        if (!userRepository.findByUsername(username).isPresent()) {
            throw new NoSuchUserException("User not found");
        }
        return userRepository.getReservationsByUsername(username);
    }
}
