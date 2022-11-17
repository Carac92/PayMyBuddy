package com.paymybuddy.app.service;

import com.paymybuddy.app.model.Contact;
import com.paymybuddy.app.model.MoneyTransfer;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface MoneyTransferService {
    void addMoneyTransfer(Principal principal, Contact contact, BigDecimal amount);
    List<MoneyTransfer> getMoneyTransfers(Principal principal);


}
