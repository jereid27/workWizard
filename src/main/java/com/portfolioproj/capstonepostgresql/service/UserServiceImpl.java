package com.portfolioproj.capstonepostgresql.service;

import com.portfolioproj.capstonepostgresql.dto.UserRegistrationDto;
import com.portfolioproj.capstonepostgresql.entities.User;
import com.portfolioproj.capstonepostgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User(userRegistrationDto.getUsername(), passwordEncoder.encode(userRegistrationDto.getPassword()), userRegistrationDto.getFullname());
        return userRepository.save(user);
    }

    @Override
    public boolean specialCharacters(String password) {
        String specialCharacters = "!@#$%^&*()-_=+[{]};:'\",<.>/?";
        for (char c : password.toCharArray()) {
            if (specialCharacters.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }
}
