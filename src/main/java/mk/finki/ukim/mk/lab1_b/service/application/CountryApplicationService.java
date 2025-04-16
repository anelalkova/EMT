package mk.finki.ukim.mk.lab1_b.service.application;

import mk.finki.ukim.mk.lab1_b.dto.CreateCountryDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> save(CreateCountryDto country);

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto country);

    void deleteById(Long id);
}
