package com.paymybuddy.app.service;

import com.paymybuddy.app.model.BankInfo;

import java.security.Principal;

public interface BankInfoService {
     void addBankInfo(Principal principal, BankInfo bankInfo);
     boolean removeBankInfoForUser(Principal principal);
    BankInfo getBankInfoForUser(Principal principal);
    boolean updateBankInfoForUser(Principal principal, BankInfo bankInfo);
}
