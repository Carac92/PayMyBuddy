package com.paymybuddy.app.service;

import com.paymybuddy.app.model.AccountTransfer;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

/**
 * interface for Account Transfer Service.
 */
public interface AccountTransferService {
    void addAccountTransfer(Principal principal, BigDecimal amount, Long BankInfoId, Boolean deposit);
    List<AccountTransfer> getAccountTransfers(Principal principal);
}
