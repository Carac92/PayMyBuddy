package com.paymybuddy.app.controller;

import com.paymybuddy.app.model.User;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "login";
    }
}
