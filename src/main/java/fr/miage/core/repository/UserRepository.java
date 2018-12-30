package fr.miage.core.repository;

import fr.miage.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String name);
    User findByuserId(Long id);
    User findByUserMail(String email);
}
