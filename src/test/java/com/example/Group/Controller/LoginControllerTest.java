package com.example.Group.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.Group.Service.CustomUser;
import com.example.Group.Service.MyCustomUserDetailsService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @InjectMocks
        private LoginController loginController;

        @Mock
        private MyCustomUserDetailsService userDetailsService;

        @Mock
        private PasswordEncoder passwordEncoder;

        @Mock
        private HttpServletRequest request;

        @Mock
        private HttpSession session;

        @Mock
        private UserDetails userDetails;

        @Mock
        private CustomUser customUser;

        @Test
        public void testShowLoginPage() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        // @Test
        // public void testLoginSuccess() throws Exception {
        // UserDetails userDetails = new User("test@example.com",
        // "$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG",
        // AuthorityUtils.createAuthorityList("ROLE_USER"));

        // when(userDetailsService.loadUserByUsername("test@example.com")).thenReturn(userDetails);
        // when(passwordEncoder.matches("password",
        // userDetails.getPassword())).thenReturn(true);

        // MockHttpServletRequestBuilder requestBuilder = post("/signin")
        // .param("email", "test@example.com")
        // .param("password", "password");
        // mockMvc.perform(requestBuilder)
        // .andExpect(status().isFound())
        // .andExpect(redirectedUrl("/home"));

        // verify(userDetailsService, times(1)).loadUserByUsername("test@example.com");
        // verify(passwordEncoder, times(1)).matches("password",
        // userDetails.getPassword());
        // }

}
