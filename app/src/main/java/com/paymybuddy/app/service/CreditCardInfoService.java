package com.paymybuddy.app.service;

import com.paymybuddy.app.model.CreditCardInfo;

import java.security.Principal;

public interface CreditCardInfoService {
    CreditCardInfo getCreditCardInfoForUser(Principal principal);
    void addCreditCardInfoForUser(CreditCardInfo creditCardInfo, Principal principal);
    boolean deleteCreditCardInfoForUser(Principal principal);
}
