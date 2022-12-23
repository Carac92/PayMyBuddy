package com.paymybuddy.app.service;

import com.paymybuddy.app.model.AccountTransfer;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface AccountTransferService {
    void addAccountTransfer(Principal principal, BigDecimal Amount, Long BankInfoId, Boolean deposit);
    List<AccountTransfer> getAccountTransfers(Principal principal);
}
