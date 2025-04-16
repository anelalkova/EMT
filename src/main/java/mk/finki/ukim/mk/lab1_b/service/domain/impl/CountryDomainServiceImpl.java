package mk.finki.ukim.mk.lab1_b.service.domain.impl;

import mk.finki.ukim.mk.lab1_b.model.Country;
import mk.finki.ukim.mk.lab1_b.repository.CountryRepository;
import mk.finki.ukim.mk.lab1_b.service.domain.CountryDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryDomainServiceImpl implements CountryDomainService {
    private final CountryRepository countryRepository;

    public CountryDomainServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> save(Country country) {
        return Optional.of(
                countryRepository.save(new Country(
                        country.getName(),
                        country.getContinent()
                )));
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countryRepository.findById(id)
                .map(existingCountry -> {
                    if (country.getName() != null) {
                        existingCountry.setName(country.getName());
                    }
                    if (country.getContinent() != null) {
                        existingCountry.setContinent(country.getContinent());
                    }
                    return countryRepository.save(existingCountry);
                });
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
