package com.example.Group.Entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "group_entity")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_users")
    private Integer numberUsers;

    @Column(name = "invitation_link")
    private String invitationLink;

    @Column(name = "is_created")
    private boolean isCreated;

    @OneToMany(mappedBy = "group")
    private List<UserGroup> userGroups;

    // Getters et setters
    public Long getId() {
        return id;
    }

    public Integer getNumberUsers() {
        return numberUsers;
    }

    public void setNumberUsers(Integer numberUsers) {
        this.numberUsers = numberUsers;
    }

    public String getinvitationLink() {
        return invitationLink;
    }

    public void setinvitationLink(String invitationLink) {
        this.invitationLink = invitationLink;
    }

    public boolean getIsCreated() {
        return isCreated;
    }

    public void setIsCreated(boolean isCreated) {
        this.isCreated = isCreated;
    }
}
