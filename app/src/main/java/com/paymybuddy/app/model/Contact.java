package com.paymybuddy.app.model;

import javax.persistence.*;

/**
 * Model for contact linked between two users.
 */
@Entity
@Table(name="Contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "contact_user_id")
    private User contactUser;


//GETTERS AND SETTERS

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public User getContactUser() {
        return contactUser;
    }

    public void setContactUser(User contactUser) {
        this.contactUser = contactUser;
    }
}
