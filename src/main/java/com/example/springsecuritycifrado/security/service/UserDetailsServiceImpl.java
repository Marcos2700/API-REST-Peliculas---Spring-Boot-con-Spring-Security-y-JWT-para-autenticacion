package com.example.springsecuritycifrado.security.service;

import com.example.springsecuritycifrado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Autentica a los usuarios en la base de datos
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.springsecuritycifrado.entities.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username no encontrado: " + username));

        return new org.springframework.security.core.userdetails.User(user.getUserame(), user.getPassword(), new ArrayList<>());
    }

}
