package com.paymybuddy.app.service;

import com.paymybuddy.app.model.BankInfo;

import java.security.Principal;
import java.util.List;

/**
 * Interface for Bank information service.
 */
public interface BankInfoService {
     void addBankInfo(Principal principal, BankInfo bankInfo);

    void deleteById(long bankInfoId);

    List<BankInfo> getBankInfosForUser(Principal principal);
    BankInfo getBankInfoById(long bankInfoId);
}
