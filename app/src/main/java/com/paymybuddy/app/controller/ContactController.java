package com.paymybuddy.app.controller;

import com.paymybuddy.app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/saveContact")
    public String saveContact(Principal principal, @ModelAttribute String contactEmail){
        contactService.addContact(principal, contactEmail);
        return "transfer";
    }

    // TODO Create rest of the controller


}
