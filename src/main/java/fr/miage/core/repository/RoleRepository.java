package fr.miage.core.repository;

import fr.miage.core.entity.Customer;
import fr.miage.core.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
