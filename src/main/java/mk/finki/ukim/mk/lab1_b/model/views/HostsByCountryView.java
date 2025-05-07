package mk.finki.ukim.mk.lab1_b.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.hosts_by_country")
@Immutable
public class HostsByCountryView {
    @Id
    @Column(name = "country")
    private String country;

    @Column(name = "host_count")
    private Long hostCount;
}
