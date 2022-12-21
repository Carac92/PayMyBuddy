package com.paymybuddy.app.service;

import com.paymybuddy.app.model.BankInfo;

import java.security.Principal;

public interface BankInfoService {
     void addBankInfo(Principal principal, BankInfo bankInfo);

    void deleteById(long bankInfoId);

    BankInfo getBankInfoForUser(Principal principal);
}
