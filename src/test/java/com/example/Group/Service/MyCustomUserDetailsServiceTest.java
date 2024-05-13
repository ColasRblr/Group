package com.example.Group.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.Group.Entity.User;
import com.example.Group.Repository.UserRepository;

@SpringBootTest
public class MyCustomUserDetailsServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MyCustomUserDetailsService myCustomUserDetailsService;

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> myCustomUserDetailsService.loadUserByUsername("test@example.com"));
    }

    @Test
    public void testLoadUserByUsername_NullEmail() {
        assertThrows(UsernameNotFoundException.class, () -> myCustomUserDetailsService.loadUserByUsername(null));
    }

    @Test
    public void testLoadUserByUsername_EmptyEmail() {
        assertThrows(UsernameNotFoundException.class, () -> myCustomUserDetailsService.loadUserByUsername(""));
    }

    @Test
    public void testLoadUserByUsername_WhitespaceEmail() {
        assertThrows(UsernameNotFoundException.class, () -> myCustomUserDetailsService.loadUserByUsername("   "));
    }

    @Test
    public void testLoadUserByUsername_InvalidEmail() {
        assertThrows(UsernameNotFoundException.class,
                () -> myCustomUserDetailsService.loadUserByUsername("testexample.com"));
    }

    @Test
    public void testLoadUserByUsername_UserFound() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setIsAdmin(true);
        user.setId(1L);

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        CustomUser customUser = (CustomUser) myCustomUserDetailsService.loadUserByUsername("test@example.com");

        assertEquals("test@example.com", customUser.getUsername());
        assertEquals("password", customUser.getPassword());
        assertEquals("John", customUser.getFirstname());
        assertEquals("Doe", customUser.getLastname());
        assertTrue(customUser.getIsAdmin());
        assertEquals(1L, customUser.getId());
    }

    @Test
    public void testLoadUserByUsername_UserFoundWithNoAuthorities() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setIsAdmin(false);
        user.setId(1L);

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        CustomUser customUser = (CustomUser) myCustomUserDetailsService.loadUserByUsername("test@example.com");

        assertEquals("test@example.com", customUser.getUsername());
        assertEquals("password", customUser.getPassword());
        assertEquals("John", customUser.getFirstname());
        assertEquals("Doe", customUser.getLastname());
        assertFalse(customUser.getIsAdmin());
        assertEquals(1L, customUser.getId());
        assertTrue(customUser.getAuthorities().isEmpty());
    }

}
