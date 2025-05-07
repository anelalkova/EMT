package mk.finki.ukim.mk.lab1_b.repository;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab1_b.model.views.HostsByCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HostsByCountryRepository extends JpaRepository<HostsByCountryView, String> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.hosts_by_country", nativeQuery = true)
    void refreshMaterializedView();
}

