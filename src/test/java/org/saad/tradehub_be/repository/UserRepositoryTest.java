package org.saad.tradehub_be.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.saad.tradehub_be.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = User.builder()
                .userId("user123")
                .username("testUser")
                .password("password")
                .email("testuser@example.com")
                .isSeller(true)
                .isVerifiedAccount(true)
                .phoneNumber("1234567890")
                .address("123 Test St")
                .zipCode("12345")
                .build();

        userRepository.save(mockUser);
    }

    @Test
    public void testFindByUsername_withValidUsername_returnsUser() {
        Optional<User> foundUser = userRepository.findByUsername("testUser");

        assertTrue(foundUser.isPresent());
        assertEquals("testUser", foundUser.get().getUsername());
        assertEquals("testuser@example.com", foundUser.get().getEmail());
    }

    @Test
    public void testFindByUsername_withInvalidUsername_returnsEmpty() {
        Optional<User> foundUser = userRepository.findByUsername("invalidUser");

        assertFalse(foundUser.isPresent());
    }

    @Test
    public void testSaveUser_andRetrieveById() {
        Optional<User> foundUser = userRepository.findById("user123");

        assertTrue(foundUser.isPresent());
        assertEquals("testUser", foundUser.get().getUsername());
        assertEquals("testuser@example.com", foundUser.get().getEmail());
    }
}
