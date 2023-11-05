package edu.school21.services;

import edu.school21.exceptions.AlwaysAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private UserRepository userRepository;
    private UserServiceImpl userService;
    private final User RETURNED_USER = new User(1L, "Nova", "12345", false);
    private final User RETURNED_USER_WITH_STATUS_TRUE = new User(1L, "Nova", "12345", true);

    @BeforeEach
    void init(){
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void isAuthenticateTrueCorrectLoginAndPassword(){
        Mockito.when(userRepository.findByLogin(RETURNED_USER.getLogin())).thenReturn(RETURNED_USER);
        assertTrue(userService.authenticate(RETURNED_USER.getLogin(), RETURNED_USER.getPassword()));
        assertTrue(RETURNED_USER.getAuthenticationStatus());
        Mockito.verify(userRepository, Mockito.times(1)).update(RETURNED_USER);
    }

    @ParameterizedTest(name = "password - {0} is wrong")
    @ValueSource(strings = {"54321", "", " "})
    void isAuthenticateFalseIncorrectPassword(String args){
        Mockito.when(userRepository.findByLogin(RETURNED_USER.getLogin())).thenReturn(RETURNED_USER);
        assertFalse(userService.authenticate(RETURNED_USER.getLogin(), args));
        assertFalse(RETURNED_USER.getAuthenticationStatus());
    }

    @Test
    void isAuthenticateThrowIncorrectLogin(){
        Mockito.when(userRepository.findByLogin("IncorrectLogin")).thenThrow(new EntityNotFoundException("IncorrectLogin"));
        assertThrows(EntityNotFoundException.class, ()-> userService.authenticate("IncorrectLogin", "password"));
    }

    @Test
    void isAuthenticateThrowStatusTrue(){
        Mockito.when(userRepository.findByLogin(RETURNED_USER_WITH_STATUS_TRUE.getLogin())).thenReturn(RETURNED_USER_WITH_STATUS_TRUE);
        assertThrows(AlwaysAuthenticatedException.class, ()-> userService.authenticate(RETURNED_USER_WITH_STATUS_TRUE.getLogin(), RETURNED_USER_WITH_STATUS_TRUE.getPassword()));
    }
}