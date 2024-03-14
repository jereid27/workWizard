package com.portfolioproj.capstonepostgresql.security;

import com.portfolioproj.capstonepostgresql.entities.User;
import com.portfolioproj.capstonepostgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("checking credentials");
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("Username or Password not found");
            throw new UsernameNotFoundException("Username or Password not found.");
        }

        System.out.println("User authenticated successfully: " + username);

        System.out.println("Found user: " + user.getUsername());
        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                authorities(),
                user.getFullname());
    }

    public Collection<? extends GrantedAuthority> authorities () {
        return List.of(new SimpleGrantedAuthority("USER"));
    };


}
