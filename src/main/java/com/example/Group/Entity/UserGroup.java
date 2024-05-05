package com.example.Group.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_group")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_group")
    private Long idGroup;

    @ManyToOne
    private Group group;

    // Getters et setters
    public Long getId() {
        return id;
    }

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
