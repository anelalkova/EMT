package mk.finki.ukim.mk.lab1_b.service.domain;

import mk.finki.ukim.mk.lab1_b.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDomainService {
    List<Country> findAll();

    Optional<Country> save(Country country);

    Optional<Country> findById(Long id);

    Optional<Country> update(Long id, Country country);

    void deleteById(Long id);
}
