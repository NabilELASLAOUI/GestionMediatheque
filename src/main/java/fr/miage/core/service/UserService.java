package fr.miage.core.service;

import fr.miage.core.entity.Customer;
import fr.miage.core.entity.User;

import java.util.List;

public interface UserService {
    User save(User entity);
    void delete(Long id);
    List<User> findAll();
    User findById(Long id);
    User findByUserName(String name);
}
