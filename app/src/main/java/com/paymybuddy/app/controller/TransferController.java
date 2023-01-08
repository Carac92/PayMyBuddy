package com.paymybuddy.app.controller;

import com.paymybuddy.app.model.Contact;
import com.paymybuddy.app.model.MoneyTransfer;
import com.paymybuddy.app.service.ContactService;
import com.paymybuddy.app.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RequestMapping("/transfer")
@Controller
public class TransferController {
    @Autowired
    private MoneyTransferService moneyTransferService;
    @Autowired
    private ContactService contactService;

    @GetMapping
    public String getTransfer(Principal principal, Model model){
        List<MoneyTransfer> moneyTransfers = moneyTransferService.getMoneyTransfers(principal);
        List<Contact> contacts = contactService.getAllContactsForConnectedUser(principal);
        model.addAttribute("transfers", moneyTransfers);
        model.addAttribute("contacts", contacts);
        return "transfer";
    }
    @PostMapping("/addTransfer")
    public String addTransfer(Principal principal, @RequestParam(value="amount")BigDecimal amount,
                              @RequestParam(value="description") String description,
                              @RequestParam(value="contactId") Long contactId) throws ChangeSetPersister.NotFoundException, ClassNotFoundException {
        moneyTransferService.addMoneyTransfer(principal, contactId, amount, description);
        return "redirect:/transfer";
    }
}
