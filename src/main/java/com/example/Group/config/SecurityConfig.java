package com.example.Group.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.Group.Service.MyCustomUserDetailsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.Group.Repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyCustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/signin", "/css/**", "/js/**", "/webjars/**").permitAll()
                        .requestMatchers("/home", "/create_users_and_groups", "/groupe/**", "/createGroup/**", "/group",
                                "/invitation/**")
                        .authenticated())
                // .anyRequest().authenticated()
                // .requestMatchers(HttpMethod.GET, "/user").hasRole("USER")
                // .requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")

                .formLogin((form) -> form
                        // .loginPage("/login")
                        // .usernameParameter("email")
                        .permitAll()
                // .defaultSuccessUrl("/home", true)
                )
                // .sessionManagement((session) -> session
                // .maximumSessions(1)
                // .expiredUrl("/login?expired"))

                .userDetailsService(userDetailsService)
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler appAuthenticationSuccessHandler() {
        return new AppAuthenticationSuccessHandler();
    }

    public class AppAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
        protected void handle(HttpServletRequest request, HttpServletResponse response,
                Authentication authentication) throws IOException, ServletException {
        }

    }
}
