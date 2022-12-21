package com.paymybuddy.app.controller;

import com.paymybuddy.app.model.Contact;
import com.paymybuddy.app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public String getContacts(Principal principal, Model model) {
        List<Contact> contacts = contactService.getAllContactsForConnectedUser(principal);
        model.addAttribute("contacts", contacts);
        return "contact";
    }
    @PostMapping("/saveContact")
    public String saveContact(Principal principal, @RequestParam(value="contactEmail") String contactEmail){
        contactService.addContact(principal, contactEmail);
        return "redirect:/transfer";
    }


    @PostMapping("/deleteContact")
    public String deleteContact(@RequestParam(value="contactId") Long id, Principal principal){
        contactService.removeContact(principal, id);
        return "redirect:/transfer";
    }
}
