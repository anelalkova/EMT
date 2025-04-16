/*
package mk.finki.ukim.mk.lab1_b.service.domain.impl;

import mk.finki.ukim.mk.lab1_b.model.Host;
import mk.finki.ukim.mk.lab1_b.repository.CountryRepository;
import mk.finki.ukim.mk.lab1_b.repository.HostRepository;
import mk.finki.ukim.mk.lab1_b.service.domain.HostDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostDomainServiceImpl implements HostDomainService {

    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public HostDomainServiceImpl(HostRepository hostRepository, CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> save(Host host) {
        if (host.getCountry() != null &&
                countryRepository.findById(host.getCountry().getId()).isPresent()) {
            return Optional.of(
                    hostRepository.save(new Host(
                            host.getName(),
                            host.getSurname(),
                            countryRepository.findById(host.getCountry().getId()).get()
                    )));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id)
                .map(existingHost -> {
                    if (host.getName() != null) {
                        existingHost.setName(existingHost.getName());
                    }
                    if (host.getSurname() != null) {
                        existingHost.setSurname(host.getSurname());
                    }
                    if (host.getCountry() != null && countryRepository.findById(host.getCountry().getId()).isPresent()) {
                        existingHost.setCountry(countryRepository.findById(host.getCountry().getId()).get());
                    }
                    return hostRepository.save(existingHost);
                });

    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }
}
*/
