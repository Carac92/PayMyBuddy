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
import java.util.Objects;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserService userService;


    @Override
    public Contact getContactWithConnectedUserAndContactId(Principal principal, String contactEmail) {
        User connectedUser = userService.findByEmail(principal.getName());
        User contactUser = userService.findByEmail(contactEmail);
        return contactRepository.findContactByUserIdAndContactUserId(connectedUser.getId(), contactUser.getId());
    }


    @Override
    public void addContact(Principal principal, String contactEmail) {
        Contact contact = new Contact();
        contact.setUser(userService.findByEmail(principal.getName()));
        contact.setContactUser(userService.findByEmail(contactEmail));
        contactRepository.save(contact);
    }


    @Override
    public List<Contact> getAllContactsForConnectedUser(Principal principal) {
        return contactRepository.findAllByUserId(userService.findByEmail(principal.getName()).getId());
    }

    @Override
    public boolean removeContact(Principal principal, String contactEmail) {
        User user = userService.findByEmail(principal.getName());
        User contactUser = userService.findByEmail(contactEmail);
        Contact contact= contactRepository.findContactByUserIdAndContactUserId(user.getId(), contactUser.getId());
        Long suppressedContact=contactRepository.deleteContactByUserIdAndContactUserId(user.getId(), contactUser.getId());
        return Objects.equals(suppressedContact, contact.getId());
    }
}
