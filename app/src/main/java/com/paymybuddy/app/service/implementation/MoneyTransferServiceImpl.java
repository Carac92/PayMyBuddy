package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.MoneyTransfer;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.MoneyTransferRepository;
import com.paymybuddy.app.service.BillService;
import com.paymybuddy.app.service.MoneyTransferService;
import com.paymybuddy.app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


/**
 * Implementation of MoneyTransferService autowired to User Service, Money Transfer Repository, Bill Service.
 * Permit to create a MoneyTransfer and to get a list of all the MoneyTransfers.
 */
@Log4j2
@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {
    @Autowired
    private UserService userService;
    @Autowired
    private MoneyTransferRepository moneyTransferRepository;
    @Autowired
    private BillService billService;

    @Override
    public void addMoneyTransfer(Principal principal, String contactEmail, BigDecimal amount, String description){
        log.info("adding money transfer between " + principal.getName() + " and " + contactEmail);
        User connectedUser = userService.findByEmail(principal.getName());
        User contactUser = userService.findByEmail(contactEmail);
        if(contactUser != null) {
            if (connectedUser.getCredit().doubleValue() >= amount.doubleValue()) {
                log.info("Money transfer added");
                MoneyTransfer moneyTransfer = new MoneyTransfer();
                moneyTransfer.setTransferDate(Date.valueOf(LocalDate.now()));
                moneyTransfer.setAmount(amount.multiply(BigDecimal.valueOf(0.95)));
                moneyTransfer.setDescription(description);
                moneyTransfer.setUser(connectedUser);
                moneyTransfer.setContact(contactUser);
                billService.addBill(principal, moneyTransfer, amount);
                moneyTransferRepository.save(moneyTransfer);

                contactUser.setCredit(contactUser.getCredit().add(moneyTransfer.getAmount()));
                userService.updateCreditForUser(contactUser);
                connectedUser.setCredit(connectedUser.getCredit().subtract(amount));
                userService.updateCreditForUser(connectedUser);
            }
        }
        log.error("Not enough credit to transfer the amount");
    }

    @Override
    public List<MoneyTransfer> getMoneyTransfers(Principal principal) {
        log.info("Getting all money transfers for " + principal.getName());
        return moneyTransferRepository.getMoneyTransferByUserId(userService.findByEmail(principal.getName()).getId());
    }
}
