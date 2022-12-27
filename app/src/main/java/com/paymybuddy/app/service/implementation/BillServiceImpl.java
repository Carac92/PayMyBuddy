package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.Bill;
import com.paymybuddy.app.model.MoneyTransfer;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.BillRepository;
import com.paymybuddy.app.service.BillService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

/**
 * Implementation of Bill Service autowired to Bill Repository and User Service.
 * Permit to create a new bill, and get a list of all bills for the connected user.
 */
@Log4j2
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserService userService;


    @Override
    public void addBill(Principal principal, MoneyTransfer moneyTransfer, BigDecimal amount) {
        log.info("adding new bill for " + principal.getName() + "for money transfer :" + moneyTransfer.getId());
        Bill bill = new Bill();
        User user = userService.findByEmail(principal.getName());
        bill.setPrice(amount.multiply(BigDecimal.valueOf(0.05)));
        bill.setUser(user);
        bill.setMoneyTransfer(moneyTransfer);
        billRepository.save(bill);
    }

    @Override
    public List<Bill> getBillList(Principal principal) {
        log.info("getting all bills for " + principal.getName());
       return billRepository.getBillsByUserId(userService.findByEmail(principal.getName()).getId());
    }
}
