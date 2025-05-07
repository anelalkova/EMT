package mk.finki.ukim.mk.lab1_b.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.accommodations_per_host")
@Immutable
public class AccommodationsPerHostView {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "num_accommodations")
    private Integer num_accommodations;
}
