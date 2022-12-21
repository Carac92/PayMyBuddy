package com.paymybuddy.app.service;

import com.paymybuddy.app.model.Contact;

import java.security.Principal;
import java.util.List;

public interface ContactService {
    Contact getContactWithConnectedUserAndContactId(Principal principal, String contactEmail);

    void addContact(Principal principal, String contactEmail);

    List<Contact> getAllContactsForConnectedUser(Principal principal);

    void removeContact(Principal principal, Long id);


}
