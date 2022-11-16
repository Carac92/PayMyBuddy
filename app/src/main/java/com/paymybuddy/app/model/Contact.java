package com.paymybuddy.app.model;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name="Contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", nullable = false)
    private Long contactID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Optional<User> user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_contact_id")
    private Optional<User> contactUser;


//GETTERS AND SETTERS

    public Long getContactID() {
        return contactID;
    }
    public void setContactID(Long contactID) {
        this.contactID = contactID;
    }
    public Optional<User> getUser() {
        return user;
    }
    public void setUser(Optional<User> user) {
        this.user = user;
    }
    public Optional<User> getContactUser() {
        return contactUser;
    }

    public void setContactUser(Optional<User> contactUser) {
        this.contactUser = contactUser;
    }
}
