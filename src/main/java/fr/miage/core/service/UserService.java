package fr.miage.core.service;

import fr.miage.core.entity.User;
import fr.miage.core.entity.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User entity);
    void delete(Long id);
    List<User> findAll();
    User findByuserId(Long id);
    Optional<User> findByUserName(String name);
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String VerificationToken);
    Optional<User> findByUserMail(String email);
}
