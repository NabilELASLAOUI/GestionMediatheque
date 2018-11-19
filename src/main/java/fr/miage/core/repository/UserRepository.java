package fr.miage.core.repository;

import fr.miage.core.entity.Customer;
import fr.miage.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String name);

}
