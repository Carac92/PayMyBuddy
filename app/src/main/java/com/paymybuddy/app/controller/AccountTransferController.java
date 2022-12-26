package com.paymybuddy.app.controller;

import com.paymybuddy.app.model.AccountTransfer;
import com.paymybuddy.app.service.AccountTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
@Controller
@RequestMapping(value = "/accountTransfer")
public class AccountTransferController {

    @Autowired
    private AccountTransferService accountTransferService;

    @PostMapping("/TransferToBank")
    public String addTransferToBank (Principal principal, BigDecimal Amount, Long bankInfoId){
        accountTransferService.addAccountTransfer(principal, Amount, bankInfoId, false);
        return "redirect:/home";
    }
    @PostMapping("/transferFromBank")
    public String addTransferFromBank (Principal principal, BigDecimal Amount, Long bankInfoId){
        accountTransferService.addAccountTransfer(principal, Amount, bankInfoId, true);
        return "redirect:/home";
    }
    @GetMapping
    public String getTransfers(Principal principal, Model model){
        List<AccountTransfer> accountTransferList = accountTransferService.getAccountTransfers(principal);
        model.addAttribute("transfers",accountTransferList);
        return "bankTransfer";
    }
}
