package com.paymybuddy.app.service;

import com.paymybuddy.app.model.Bill;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface BillService {
    void addBill(Principal principal, BigDecimal amount);
    List<Bill> getBillList(Principal principal);
}
