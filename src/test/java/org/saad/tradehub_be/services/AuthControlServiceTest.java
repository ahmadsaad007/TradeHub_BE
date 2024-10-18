package org.saad.tradehub_be.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.saad.tradehub_be.boundary.request.LoginRequest;
import org.saad.tradehub_be.boundary.request.SignUpRequest;
import org.saad.tradehub_be.data.User;
import org.saad.tradehub_be.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControlServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthControlService authControlService;

    @Test
    public void testLoginUser_withValidCredentials_returnsTrue() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("password");

        User mockUser = new User();
        mockUser.setPassword("password");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(mockUser));

        boolean result = authControlService.loginUser(loginRequest);
        assertTrue(result);
    }

    @Test
    public void testLoginUser_withInvalidCredentials_returnsFalse() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("wrongpassword");

        User mockUser = new User();
        mockUser.setPassword("password");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(mockUser));
        boolean result = authControlService.loginUser(loginRequest);
        assertFalse(result);
    }

    @Test
    public void testRegisterUser_withValidData() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testuser");
        signUpRequest.setPassword("password");
        signUpRequest.setEmail("test@example.com");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        authControlService.registerUser(signUpRequest);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testRegisterUser_withExistingUsername_throwsException() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testuser");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(new User()));

        assertThrows(RuntimeException.class, () -> authControlService.registerUser(signUpRequest));
    }
}
