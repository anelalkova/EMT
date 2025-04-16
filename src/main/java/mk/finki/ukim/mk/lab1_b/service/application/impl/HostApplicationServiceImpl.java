/*
package mk.finki.ukim.mk.lab1_b.service.application.impl;

import mk.finki.ukim.mk.lab1_b.dto.CreateHostDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayHostDto;
import mk.finki.ukim.mk.lab1_b.model.Country;
import mk.finki.ukim.mk.lab1_b.service.application.HostApplicationService;
import mk.finki.ukim.mk.lab1_b.service.domain.CountryDomainService;
import mk.finki.ukim.mk.lab1_b.service.domain.HostDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostDomainService hostDomainService;
    private final CountryDomainService countryDomainService;

    public HostApplicationServiceImpl(HostDomainService hostDomainService, CountryDomainService countryDomainService) {
        this.hostDomainService = hostDomainService;
        this.countryDomainService = countryDomainService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return hostDomainService.findAll().stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto host) {
        Optional<Country> country = countryDomainService.findById(host.country());

        if (country.isPresent()) {
            return hostDomainService.save(host.toHost(country.get()))
                    .map(DisplayHostDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostDomainService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto host) {
        Optional<Country> country = countryDomainService.findById(host.country());

        return hostDomainService.update(id,
                        host.toHost(
                                country.get()
                        )
                )
                .map(DisplayHostDto::from);
    }

    @Override
    public void deleteById(Long id) {
        hostDomainService.deleteById(id);
    }
}
*/
