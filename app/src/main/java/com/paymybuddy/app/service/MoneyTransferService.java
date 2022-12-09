package com.paymybuddy.app.service;

import com.paymybuddy.app.model.Contact;
import com.paymybuddy.app.model.MoneyTransfer;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface MoneyTransferService {
    void addMoneyTransfer(Principal principal, String contact, BigDecimal amount) throws ClassNotFoundException, ChangeSetPersister.NotFoundException;
    List<MoneyTransfer> getMoneyTransfers(Principal principal);


}
