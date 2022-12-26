package com.paymybuddy.app.service;

import com.paymybuddy.app.model.Bill;
import com.paymybuddy.app.model.MoneyTransfer;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

/**
 * Interface for the Bill Service.
 */
public interface BillService {
    void addBill(Principal principal, MoneyTransfer moneyTransfer, BigDecimal amount);
    List<Bill> getBillList(Principal principal);
}
