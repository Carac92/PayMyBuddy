package com.paymybuddy.app.controller;

import com.paymybuddy.app.service.UserService;
import com.paymybuddy.app.service.implementation.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
