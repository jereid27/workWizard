package com.portfolioproj.capstonepostgresql.service;

import com.portfolioproj.capstonepostgresql.dto.UserRegistrationDto;
import com.portfolioproj.capstonepostgresql.entities.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    User findByUsername(String username);
    User save (UserRegistrationDto userRegistrationDto);

    boolean specialCharacters(String password);

}