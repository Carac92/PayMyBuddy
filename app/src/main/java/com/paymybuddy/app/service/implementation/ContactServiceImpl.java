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

@Log4j2
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserService userService;


    @Override
    public Contact getContactWithConnectedUserAndContactId(Principal principal, String contactEmail) {
        log.info("getting contact at the mail : " + contactEmail + " for user " + principal.getName());
        User connectedUser = userService.findByEmail(principal.getName());
        User contactUser = userService.findByEmail(contactEmail);
        return contactRepository.findContactByUserIdAndContactUserId(connectedUser.getId(), contactUser.getId());
    }


    @Override
    public void addContact(Principal principal, String contactEmail) {
        log.info("creating contact");
        Contact contact = new Contact();
        User contactUser = userService.findByEmail(contactEmail);
        if(contactUser!=null) {
            log.info("creating contact between " + principal.getName() + " and " + contactEmail);
            contact.setUser(userService.findByEmail(principal.getName()));
            contact.setContactUser(contactUser);
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
