package mk.finki.ukim.mk.lab1_b.dto;

import mk.finki.ukim.mk.lab1_b.model.Accommodation;
import mk.finki.ukim.mk.lab1_b.model.Availability;
import mk.finki.ukim.mk.lab1_b.model.Country;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayAvailabilityDto(
        Long id,
        Date reservedFrom,
        Date reservedTo,
        Double price,
        Long accommodation
) {

    public static DisplayAvailabilityDto from(Availability availability) {
        return new DisplayAvailabilityDto(
                availability.getId(),
                availability.getReservedFrom(),
                availability.getReservedTo(),
                availability.getPrice(),
                availability.getAccommodation().getId()
        );
    }

    public static List<DisplayAvailabilityDto> from(List<Availability> availabilities) {
        return availabilities.stream().map(DisplayAvailabilityDto::from).collect(Collectors.toList());
    }

    public Availability toAvailability(Accommodation accommodation) {
        return new Availability(
                reservedFrom,reservedTo,price, accommodation);
    }
}