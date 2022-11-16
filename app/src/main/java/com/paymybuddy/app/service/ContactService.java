package com.paymybuddy.app.service;

import com.paymybuddy.app.model.Contact;

import java.util.List;

public interface ContactService {
    Contact getContactByUserIdAndContactId(Long userId, Long contactId);

    void addContact(Long connectedUserId, Long contactUserId);

    List<Contact> getAllContactsByUserId(Long userId);


}
