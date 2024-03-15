package com.portfolioproj.capstonepostgresql.service;

import com.portfolioproj.capstonepostgresql.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private final UserServiceImpl userService = new UserServiceImpl(userRepository);

    @Test
    void specialCharacters_ShouldReturnTrue_WhenPasswordContainsSpecialCharacter() {

        String password = "password!";
        boolean result = userService.specialCharacters(password);
        assertTrue(result, "Expected password to contain a special character but it did not.");
    }

}