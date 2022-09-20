package com.zavrsnirad.digitalhealth.service;

import com.zavrsnirad.digitalhealth.model.User;
import com.zavrsnirad.digitalhealth.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getEmail()
                    , user.get().getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(user.get().getRole().getName())));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
