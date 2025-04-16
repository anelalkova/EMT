package mk.finki.ukim.mk.lab1_b.service.application.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab1_b.dto.CreateAccommodationDto;
import mk.finki.ukim.mk.lab1_b.dto.CreateReservationDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayAccommodationDto;
import mk.finki.ukim.mk.lab1_b.model.Accommodation;
import mk.finki.ukim.mk.lab1_b.model.AppUser;
import mk.finki.ukim.mk.lab1_b.service.application.AccommodationApplicationService;
import mk.finki.ukim.mk.lab1_b.service.domain.AccommodationDomainService;
import mk.finki.ukim.mk.lab1_b.service.domain.UserDomainService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final UserDomainService userDomainService;
    private final AccommodationDomainService accommodationDomainService;

    public AccommodationApplicationServiceImpl(AccommodationDomainService accommodationDomainService, UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
        this.accommodationDomainService = accommodationDomainService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accommodationDomainService.findAll().stream().map(DisplayAccommodationDto::from).toList();
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodationDto) {
        Optional<AppUser> hostOpt = userDomainService.findByUsernameOptional(accommodationDto.hostUsername());

        if (hostOpt.isEmpty()) {
            return Optional.empty();
        }

        AppUser host = hostOpt.get();
        boolean isHost = host.getAuthorities().stream()
                .anyMatch(auth -> "ROLE_HOST".equals(auth.getAuthority()));

        if (isHost) {
            return accommodationDomainService.save(accommodationDto.toAccommodation(host))
                    .map(DisplayAccommodationDto::from);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationDomainService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodationDto) {
        Optional<AppUser> hostOpt = userDomainService.findByUsernameOptional(accommodationDto.hostUsername());

        if (hostOpt.isEmpty()) {
            return Optional.empty();
        }

        return accommodationDomainService.update(id, accommodationDto.toAccommodation(hostOpt.get()))
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        accommodationDomainService.deleteById(id);
    }

    @Override
    @Transactional
    public void checkinRoom(Long id) {
        accommodationDomainService.checkinRoom(id);
    }

    @Override
    @Transactional
    public void checkoutRoom(Long id) {
        accommodationDomainService.checkoutRoom(id);
    }

    @Override
    public void addReservation(CreateReservationDto createReservationDto) {
        Optional<AppUser> userOpt = userDomainService.findByUsernameOptional(createReservationDto.username());

        if (userOpt.isPresent()) {
            AppUser user = userOpt.get();
            boolean isUser = user.getAuthorities().stream()
                    .anyMatch(auth -> "ROLE_USER".equals(auth.getAuthority()));

            if (isUser) {
                accommodationDomainService.addReservation(createReservationDto.username(), createReservationDto.accommodationId());
            }
        }
    }

    @Override
    public void confirmReservations(String username) {
        if (username != null) {
            accommodationDomainService.confirmReservations(username);
        }
    }

    @Override
    public List<DisplayAccommodationDto> getReservationsForUser(String username) {
        Optional<AppUser> userOpt = userDomainService.findByUsernameOptional(username);

        if (userOpt.isPresent()) {
            AppUser user = userOpt.get();
            boolean isUser = user.getAuthorities().stream()
                    .anyMatch(auth -> "ROLE_USER".equals(auth.getAuthority()));

            if (isUser) {
                return accommodationDomainService.getReservationsForUser(user.getUsername()).stream()
                        .map(DisplayAccommodationDto::from)
                        .collect(Collectors.toList());
            }
        }

        return Collections.emptyList();
    }
}
