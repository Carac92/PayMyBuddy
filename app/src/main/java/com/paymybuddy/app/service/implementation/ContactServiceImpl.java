package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.Contact;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.ContactRepository;
import com.paymybuddy.app.service.ContactService;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserService userService;


    @Override
    public Contact getContactByUserIdAndContactId(Long userId, Long contactId) {
        return contactRepository.findContactByUserIdAndContactId(userId, contactId);
    }


    @Override
    public void addContact(Long connectedUserId, Long contactUserId) {
        Contact contact = new Contact();
        contact.setUser(userService.getById(connectedUserId));
        contact.setContactUser(userService.getById(connectedUserId));
    }


    @Override
    public List<Contact> getAllContactsByUserId(Long userId) {
        return contactRepository.findContactsByUserId(userId);
    }
}
