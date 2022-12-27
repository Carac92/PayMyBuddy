package com.paymybuddy.app.controller;

import com.paymybuddy.app.model.User;
import com.paymybuddy.app.service.MoneyTransferService;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomePageController {

    @Autowired
    private UserService userService;

    @Autowired
    private MoneyTransferService moneyTransferService;

    @GetMapping("/home")
    public String home(Principal principal, Model model) {
        User connectedUser = userService.findByEmail(principal.getName());
        model.addAttribute("firstName", connectedUser.getFirstName());
        model.addAttribute("balance", connectedUser.getCredit());
        return "home";
    }
}
