package fr.miage.core.service;

import fr.miage.core.entity.Customer;
import fr.miage.core.entity.User;
import fr.miage.core.entity.VerificationToken;

import java.util.List;

public interface UserService {
    User save(User entity);
    void delete(Long id);
    List<User> findAll();
    User findByuserId(Long id);
    User findByUserName(String name);
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String VerificationToken);
    User findByUserMail(String email);
}
