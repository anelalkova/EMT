package mk.finki.ukim.mk.lab1_b.mappers;

import mk.finki.ukim.mk.lab1_b.dto.CreateAvailabilityDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayAvailabilityDto;
import mk.finki.ukim.mk.lab1_b.model.Availability;
import mk.finki.ukim.mk.lab1_b.repository.AccommodationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AvailabilityMapper {

    private final AccommodationRepository accommodationRepository;

    public AvailabilityMapper(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    public DisplayAvailabilityDto toDto(Availability availability) {
        return new DisplayAvailabilityDto(
                availability.getId(),
                availability.getReservedFrom(),
                availability.getReservedTo(),
                availability.getPrice(),
                availability.getAccommodation().getId()
        );
    }

    public Optional<Availability> fromDto(CreateAvailabilityDto dto) {
        return accommodationRepository.findById(dto.accommodationId())
                .map(accommodation -> new Availability(
                        dto.reservedFrom(),
                        dto.reservedTo(),
                        dto.price(),
                        accommodation
                ));
    }

    public Optional<Availability> updateFromDto(Availability existing, CreateAvailabilityDto dto) {
        return accommodationRepository.findById(dto.accommodationId())
                .map(accommodation -> {
                    existing.setReservedFrom(dto.reservedFrom());
                    existing.setReservedTo(dto.reservedTo());
                    existing.setPrice(dto.price());
                    existing.setAccommodation(accommodation);
                    return existing;
                });
    }
}
