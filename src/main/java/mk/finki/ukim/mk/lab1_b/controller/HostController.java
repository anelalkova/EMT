package mk.finki.ukim.mk.lab1_b.controller;

import mk.finki.ukim.mk.lab1_b.dto.AccommodationHostDto;
import mk.finki.ukim.mk.lab1_b.dto.HostCountryDto;
import mk.finki.ukim.mk.lab1_b.model.enumerations.Role;
import mk.finki.ukim.mk.lab1_b.model.projections.HostProjection;
import mk.finki.ukim.mk.lab1_b.model.views.HostsByCountryView;
import mk.finki.ukim.mk.lab1_b.repository.HostsByCountryRepository;
import mk.finki.ukim.mk.lab1_b.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    private final HostsByCountryRepository hostsByCountryRepository;
    private final UserRepository userRepository;

    public HostController(HostsByCountryRepository hostsByCountryRepository, UserRepository userRepository) {
        this.hostsByCountryRepository = hostsByCountryRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/by-country")
    public List<HostCountryDto> getHostCountByCountry() {
        return hostsByCountryRepository.findAll().stream()
                .map(e -> new HostCountryDto(e.getCountry(), e.getHostCount()))
                .collect(Collectors.toList());
    }

    @GetMapping("/names")
    public List<HostProjection> getHostNames() {
        return userRepository.findByRole(Role.ROLE_HOST);
    }
}
