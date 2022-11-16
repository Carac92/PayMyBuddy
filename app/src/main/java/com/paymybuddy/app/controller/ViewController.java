package com.paymybuddy.app.controller;

import com.paymybuddy.app.model.User;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;


@Controller
public class ViewController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(@ModelAttribute User user) {
        return "register";
    }
    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("credit", user.getCredit());
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}