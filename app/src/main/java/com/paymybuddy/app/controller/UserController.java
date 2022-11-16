package com.paymybuddy.app.controller;

import com.paymybuddy.app.model.User;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.DateFormat;

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
    public String modifyUser(Principal principal, @RequestBody User modifiedUser) {
        userService.updateUserById(principal, modifiedUser);
        return "home";
    }
}
