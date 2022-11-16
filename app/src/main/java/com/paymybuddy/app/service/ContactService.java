package com.paymybuddy.app.service;

import com.paymybuddy.app.model.Contact;

import java.security.Principal;
import java.util.List;

public interface ContactService {
    Contact getContactByUserIdAndContactId(Long userId, Long contactId);

    void addContact(Principal principal, String contactEmail);

    List<Contact> getAllContactsByUserId(Long userId);


}
