package com.appdiscipulado.backend.config.security;

import com.appdiscipulado.backend.domain.VO.User;
import com.appdiscipulado.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<User> userLoaded = userRepository.findByEmail(username);
        if (userLoaded.isPresent()) {
            return userLoaded.get();
        }

        throw new UsernameNotFoundException("Dados inválidos!");
    }
}