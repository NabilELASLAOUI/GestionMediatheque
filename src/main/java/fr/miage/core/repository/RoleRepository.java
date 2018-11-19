package fr.miage.core.repository;

import fr.miage.core.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String name);

}
