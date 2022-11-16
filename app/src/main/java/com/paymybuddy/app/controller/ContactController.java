package com.paymybuddy.app.controller;

import com.paymybuddy.app.model.User;
import com.paymybuddy.app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/saveContact")
    public String saveContact(@AuthenticationPrincipal User user, @RequestParam Long connectedUserId){

        contactService.addContact(user.getUserID(), connectedUserId);

        return "transfer";
    }

}
