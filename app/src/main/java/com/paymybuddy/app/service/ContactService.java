package com.paymybuddy.app.service;

import com.paymybuddy.app.model.Contact;

import java.security.Principal;
import java.util.List;

/**
 * Interface for Contact service
 */
public interface ContactService {

    Contact getContactById(Long id);

    void addContact(Principal principal, String contactEmail);

    List<Contact> getAllContactsForConnectedUser(Principal principal);

    void removeContact(Principal principal, Long id);


}
