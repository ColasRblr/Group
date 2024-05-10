package com.example.Group.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LoginFormTest {

    @Test
    public void testEmptyConstructor() {
        // Arrange
        LoginForm loginForm = new LoginForm();

        // Assert
        assertNull(loginForm.getEmail());
        assertNull(loginForm.getPassword());
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        LoginForm loginForm = new LoginForm();

        // Act
        loginForm.setEmail(email);
        loginForm.setPassword(password);

        // Assert
        assertEquals(email, loginForm.getEmail());
        assertEquals(password, loginForm.getPassword());
    }

    @Test
    public void testParameterizedConstructor() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";

        // Act
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail(email);
        loginForm.setPassword(password);

        // Assert
        assertEquals(email, loginForm.getEmail());
        assertEquals(password, loginForm.getPassword());
    }
}
