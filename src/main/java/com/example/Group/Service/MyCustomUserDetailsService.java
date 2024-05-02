package com.example.Group.Service;

import com.example.Group.Entity.User;
import com.example.Group.Service.CustomUser;
import com.example.Group.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable avec l'email: " + email));

        return new CustomUser(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList(),
                user.getFirstname(),
                user.getLastname(),
                user.getIsAdmin(),
                user.getId()
        );
    }
}
