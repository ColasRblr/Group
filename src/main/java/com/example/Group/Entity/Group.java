package com.example.Group.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="group")
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

    // Getters et setters
    public Long getId() {
        return id;
    }

    public Integer getnumberUsers() {
        return numberUsers;
    }

    public void setnumberUsers(Integer numberUsers) {
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

    public void setisCreated(boolean isCreated) {
        this.isCreated = isCreated;
    }
}
