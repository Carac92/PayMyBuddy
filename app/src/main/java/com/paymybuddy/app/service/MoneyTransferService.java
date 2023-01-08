package com.paymybuddy.app.service;

import com.paymybuddy.app.model.MoneyTransfer;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

/**
 * Interface for Money transfer Service.
 */
public interface MoneyTransferService {
    void addMoneyTransfer(Principal principal, Long contactId, BigDecimal amount, String description) throws ClassNotFoundException, ChangeSetPersister.NotFoundException;
    List<MoneyTransfer> getMoneyTransfers(Principal principal);


}
