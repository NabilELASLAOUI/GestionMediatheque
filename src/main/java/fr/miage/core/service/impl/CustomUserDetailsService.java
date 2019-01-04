package fr.miage.core.service.impl;

import fr.miage.core.entity.CustomUserDetails;
import fr.miage.core.entity.User;
import fr.miage.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
         Optional<User> user = userRepository.findByUserMail(userEmail);
         user.orElseThrow(() -> new UsernameNotFoundException("User mail not found"));
        return user.map(CustomUserDetails::new).get();
    }
}
