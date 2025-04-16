package mk.finki.ukim.mk.lab1_b.repository;

import mk.finki.ukim.mk.lab1_b.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    @Query("SELECT a FROM Accommodation a JOIN a.users u WHERE u.username = :username")
    List<Accommodation> findByUsernameReservations(String username);
}
