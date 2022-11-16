package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.Contact;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.ContactRepository;
import com.paymybuddy.app.service.ContactService;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
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
    public void addContact(Principal principal, Long userId) {
        Contact contact = new Contact();
        User contactedUser = userService.findByEmail(principal.getName());
        contact.setContactUser(contactedUser);
        Optional<User> user = userService.getById(userId);
        contact.setUser(user);
        contactRepository.save(contact);
    }


    @Override
    public List<Contact> getAllContactsByUserId(Long userId) {
        return contactRepository.findContactsByUserId(userId);
    }
}
