package com.example.Group.Service;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomUserServiceTest {

    @Test
    public void testGetFirstname() {
        // Arrange
        CustomUser customUser = new CustomUser("testUser", "testPassword",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")), "John", "Doe", false, 1L);

        // Act
        String firstName = customUser.getFirstname();

        // Assert
        assertEquals("John", firstName);
    }

    @Test
    public void testGetLastname() {
        // Arrange
        CustomUser customUser = new CustomUser("testUser", "testPassword",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")), "John", "Doe", false, 1L);

        // Act
        String lastName = customUser.getLastname();

        // Assert
        assertEquals("Doe", lastName);
    }

    @Test
    public void testGetIsAdmin() {
        // Arrange
        CustomUser customUser = new CustomUser("testUser", "testPassword",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")), "John", "Doe", false, 1L);

        // Act
        boolean isAdmin = customUser.getIsAdmin();

        // Assert
        assertEquals(false, isAdmin);
    }

    @Test
    public void testGetId() {
        // Arrange
        CustomUser customUser = new CustomUser("testUser", "testPassword",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")), "John", "Doe", false, 1L);

        // Act
        Long id = customUser.getId();

        // Assert
        assertEquals(1L, id);
    }
}
