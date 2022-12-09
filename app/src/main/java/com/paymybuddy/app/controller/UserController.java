package com.paymybuddy.app.controller;

import com.paymybuddy.app.model.User;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(@ModelAttribute User user) {
        return "register";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/login";
    }
    @PutMapping("/user/{id}")
    public String modifyUser(@PathVariable Integer id, Principal principal, @ModelAttribute User modifiedUser) {
        userService.updateConnectedUser(principal, modifiedUser);
        return "profile";
    }
}
