package com.example.Group.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Group.Service.MyCustomUserDetailsService;
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
                .requestMatchers("/", "/login", "/css/**", "/js/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                // .requestMatchers(HttpMethod.GET, "/user").hasRole("USER")
				// .requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
            )
            .formLogin((form) -> form
                .loginPage("/")
                .usernameParameter("email")
                .permitAll()
                .defaultSuccessUrl("/accueil.html",true)
            )
            .logout((logout) -> logout.permitAll())
            .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
	// UserDetailsService myUserDetailsService(UserRepository userRepository) {
	// 	return new MyUserDetailsService(userRepository);
	// }
}
