package fr.miage.core.service.impl;

import fr.miage.core.entity.User;
import fr.miage.core.repository.UserRepository;
import fr.miage.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
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

}