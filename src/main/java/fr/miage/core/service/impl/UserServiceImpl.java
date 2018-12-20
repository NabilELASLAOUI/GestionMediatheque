package fr.miage.core.service.impl;

import fr.miage.core.entity.User;
import fr.miage.core.entity.VerificationToken;
import fr.miage.core.repository.UserRepository;
import fr.miage.core.repository.VerificationTokenRepository;
import fr.miage.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Override
    public User save(User entity) {
        if (!emailExist(entity.getUserMail())) {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            return userRepository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findByuserId(Long id) {
        return userRepository.findByuserId(id);
    }


    @Override
    public User findByUserName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
    private boolean emailExist(String email) {
        User user = userRepository.findByUserMail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public User findByUserMail(String email) {
        return userRepository.findByUserMail(email);
    }

}