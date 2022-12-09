package com.paymybuddy.app.controller;

import com.paymybuddy.app.model.User;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(value ="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "login";
    }
    @PutMapping("/{id}")
    public String modifyUser(Principal principal, @ModelAttribute User modifiedUser) {
        userService.updateConnectedUser(principal, modifiedUser);
        return "home";
    }
}
