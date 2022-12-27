package com.paymybuddy.app.controller;

import com.paymybuddy.app.model.AccountTransfer;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.service.AccountTransferService;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomePageController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountTransferService accountTransferService;


    @GetMapping
    public String home(Principal principal, Model model) {
        User connectedUser = userService.findByEmail(principal.getName());
        model.addAttribute("firstName", connectedUser.getFirstName());
        model.addAttribute("balance", connectedUser.getCredit());
        return "home";
    }
    @GetMapping("/accountTransfer")
    public String getTransfers(Principal principal, Model model){
        List<AccountTransfer> accountTransferList = accountTransferService.getAccountTransfers(principal);
        model.addAttribute("transfers",accountTransferList);
        return "bankTransfer";
    }
    @PostMapping("/transferToBank")
    public String addTransferToBank (Principal principal, @RequestParam(value = "amount") BigDecimal Amount,
                                     @RequestParam(value = "bankId") Long bankInfoId){
        accountTransferService.addAccountTransfer(principal, Amount, bankInfoId, false);
        return "redirect:/home";
    }
    @PostMapping("/transferFromBank")
    public String addTransferFromBank (Principal principal,@RequestParam(value = "amount") BigDecimal Amount,
                                       @RequestParam(value = "bankId") Long bankInfoId){
        accountTransferService.addAccountTransfer(principal, Amount, bankInfoId, true);
        return "redirect:/home";
    }

}
