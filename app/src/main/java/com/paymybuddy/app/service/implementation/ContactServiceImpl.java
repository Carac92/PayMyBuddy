package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.Contact;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.ContactRepository;
import com.paymybuddy.app.service.ContactService;
import com.paymybuddy.app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

/**
 * Implementation of Contact Service autowired to Contact Repository and User Service.
 * Permits to get contact with connected user and contact email, to add contact for connected user with email address of
 * the contact, get a list of all contacts, remove a contact with his id.
 */
@Log4j2
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserService userService;



    @Override
    public Contact getContactById(Long id) {
        return contactRepository.getContactById(id);
    }


    @Override
    public void addContact(Principal principal, String contactEmail) {
        log.info("creating contact");
        Contact contact = new Contact();
        User contactUser = userService.findByEmail(contactEmail);
        if(contactUser!=null) {
            log.info("creating contact between " + principal.getName() + " and " + contactEmail);
            contact.setUser(userService.findByEmail(principal.getName()));
            contact.setEmail(contactEmail);
            contact.setFirstName(contactUser.getFirstName());
            contact.setLastName(contactUser.getLastName());
            contactRepository.save(contact);
        }
        log.error("User doesn't exist with the email" + contactEmail);
    }


    @Override
    public List<Contact> getAllContactsForConnectedUser(Principal principal) {
        log.info("getting all contacts for connected user : " + principal.getName());
        return contactRepository.findAllByUserId(userService.findByEmail(principal.getName()).getId());
    }

    @Override
    public void removeContact(Principal principal, Long id) {
       log.info("deleting contact : " + id);
        contactRepository.deleteById(id);
    }
}
