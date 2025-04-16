/*
package mk.finki.ukim.mk.lab1_b.controller;

import mk.finki.ukim.mk.lab1_b.dto.CreateHostDto;
import mk.finki.ukim.mk.lab1_b.dto.DisplayHostDto;
import mk.finki.ukim.mk.lab1_b.model.Host;
import mk.finki.ukim.mk.lab1_b.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    private final HostApplicationService hostService;

    public HostController(HostApplicationService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public List<DisplayHostDto> getAll() {
        return hostService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> getById(Long id) {
        return hostService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> add(@RequestBody CreateHostDto host) {
        return hostService.save(host).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto host) {
        return hostService.update(id, host).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        if (hostService.findById(id).isPresent()) {
            hostService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
*/
