package com.paymybuddy.app.controller;

import com.paymybuddy.app.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MoneyTransferController {
    @Autowired
    private MoneyTransferService moneyTransferService;

    // TODO create controller for moneyTransfer
}
