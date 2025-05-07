package mk.finki.ukim.mk.lab1_b.repository;

import mk.finki.ukim.mk.lab1_b.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findAvailabilityByAccommodationId(Long accommodationId);

    @Query("SELECT a FROM Availability a WHERE a.accommodation.host.username = :username")
    List<Availability> findByHostUsername(@Param("username") String username);
}
