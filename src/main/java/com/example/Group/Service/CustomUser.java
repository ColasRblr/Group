package com.example.Group.Service;

import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;


public class CustomUser extends org.springframework.security.core.userdetails.User {

    private final String firstName;
    private final String lastName;
    private final boolean isAdmin;
    private final Long id;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String firstName, String lastName, boolean isAdmin, Long id) {
        super(username, password, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.id = id;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public Long getId() {
        return id;
    }
}
