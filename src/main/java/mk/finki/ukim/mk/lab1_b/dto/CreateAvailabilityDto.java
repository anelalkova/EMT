package mk.finki.ukim.mk.lab1_b.dto;

import mk.finki.ukim.mk.lab1_b.model.Accommodation;
import mk.finki.ukim.mk.lab1_b.model.Availability;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public record CreateAvailabilityDto(
        Date reservedFrom,
        Date reservedTo,
        Double price,
        Long accommodationId
) {

    public static CreateAvailabilityDto from(Availability availability) {
        return new CreateAvailabilityDto(
                availability.getReservedFrom(),
                availability.getReservedTo(),
                availability.getPrice(),
                availability.getAccommodation().getId()
        );
    }

    public static List<CreateAvailabilityDto> from(List<Availability> availabilities) {
        return availabilities.stream().map(CreateAvailabilityDto::from).collect(Collectors.toList());
    }

    public Availability toAvailability(Accommodation accommodation) {
        return new Availability(reservedFrom, reservedTo, price, accommodation);
    }
}