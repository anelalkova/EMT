package mk.finki.ukim.mk.lab1_b.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import mk.finki.ukim.mk.lab1_b.model.enumerations.Category;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Enumerated(EnumType.STRING)
    Category category;
    @ManyToOne
    AppUser host;
    Integer numRooms;
    Integer numAvailableRooms;
    @OneToMany
    List<Availability> availability;

    @ManyToMany(mappedBy = "reservations")
    private List<AppUser> users;
    public Accommodation() {}

    public Accommodation(String name, Category category, AppUser host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.numAvailableRooms = numRooms;
        this.availability = new ArrayList<>();
    }
}
