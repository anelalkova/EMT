package mk.finki.ukim.mk.lab1_b.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date reservedFrom;
    private Date reservedTo;
    private Double price;
    @ManyToOne
    private Accommodation accommodation;

    public Availability() {}

    public Availability(Date reservedFrom, Date reservedTo, Double price, Accommodation accommodation) {
        this.reservedFrom = reservedFrom;
        this.reservedTo = reservedTo;
        this.price = price;
        this.accommodation = accommodation;
    }
}

