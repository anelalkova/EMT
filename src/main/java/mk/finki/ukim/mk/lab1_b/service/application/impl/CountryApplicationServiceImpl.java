package mk.finki.ukim.mk.lab1_b.service.application.impl;

import mk.finki.ukim.mk.lab1_b.dto.CreateCountryDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayCountryDto;
import mk.finki.ukim.mk.lab1_b.service.application.CountryApplicationService;
import mk.finki.ukim.mk.lab1_b.service.domain.CountryDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryDomainService countryDomainService;

    public CountryApplicationServiceImpl(CountryDomainService countryDomainService) {
        this.countryDomainService = countryDomainService;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return countryDomainService.findAll().stream().map(DisplayCountryDto::from).toList();
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto country) {
        return countryDomainService.save(country.toCountry())
                .map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryDomainService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto country) {
        return countryDomainService.update(id,
                        country.toCountry())
                .map(DisplayCountryDto::from);
    }

    @Override
    public void deleteById(Long id) {
        countryDomainService.deleteById(id);
    }
}
