package fr.miage.core.service;

import fr.miage.core.entity.Customer;
import fr.miage.core.entity.Role;
import fr.miage.core.entity.User;

import java.util.List;

public interface RoleService {
    Role save(Role entity);
    void delete(Long id);
    List<Role> findAll();
    Role findById(Long id);
    Role findByName(String name);
}
