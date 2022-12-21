package com.paymybuddy.app.controller;

import com.paymybuddy.app.model.Bill;
import com.paymybuddy.app.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    //TODO create HTML Page


}
