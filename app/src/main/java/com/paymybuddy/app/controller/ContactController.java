package com.paymybuddy.app.controller;

import com.paymybuddy.app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/saveContact")
    public String saveContact(Principal principal, @RequestParam String contactEmail){

        contactService.addContact(principal, contactEmail);

        return "transfer";
    }

}
