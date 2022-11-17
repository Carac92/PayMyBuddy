package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.Contact;
import com.paymybuddy.app.model.MoneyTransfer;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.MoneyTransferRepository;
import com.paymybuddy.app.service.ContactService;
import com.paymybuddy.app.service.MoneyTransferService;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {
    @Autowired
    private UserService userService;
    @Autowired
    private MoneyTransferRepository moneyTransferRepository;
    @Autowired
    private ContactService contactService;

    @Override
    public void addMoneyTransfer(Principal principal, Contact contact, BigDecimal amount) {
        User connectedUser = userService.findByEmail(principal.getName());
        MoneyTransfer moneyTransfer = new MoneyTransfer();
        moneyTransfer.setTransferDate(Date.valueOf(LocalDate.now()));
        moneyTransfer.setAmount(amount.multiply(BigDecimal.valueOf(0.95)));
        moneyTransfer.setUser(connectedUser);
        moneyTransfer.setContact(contact);
        User targetUser = userService.getById(contact.getContactUser().getUserID()).get();
        targetUser.setCredit(targetUser.getCredit().add(moneyTransfer.getAmount()));
        connectedUser.setCredit(connectedUser.getCredit().subtract(moneyTransfer.getAmount()));
    }

    @Override
    public List<MoneyTransfer> getMoneyTransfers(Principal principal) {
        return userService.findByEmail(principal.getName()).getMoneyTransfers();
    }
}
