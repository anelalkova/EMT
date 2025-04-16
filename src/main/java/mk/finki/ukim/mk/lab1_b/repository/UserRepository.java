package mk.finki.ukim.mk.lab1_b.repository;

import mk.finki.ukim.mk.lab1_b.model.Accommodation;
import mk.finki.ukim.mk.lab1_b.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsernameAndPassword(String username, String password);

    Optional<AppUser> findByUsername(String username);

    @Query("SELECT a FROM Accommodation a JOIN a.users u WHERE u.username = :username")
    List<Accommodation> getReservationsByUsername(@Param("username") String username);
}

