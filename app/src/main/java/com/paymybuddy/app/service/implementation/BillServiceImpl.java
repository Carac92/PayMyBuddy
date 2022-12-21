package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.Bill;
import com.paymybuddy.app.model.MoneyTransfer;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.BillRepository;
import com.paymybuddy.app.service.BillService;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserService userService;


    @Override
    public void addBill(Principal principal, MoneyTransfer moneyTransfer, BigDecimal amount) {
        Bill bill = new Bill();
        User user = userService.findByEmail(principal.getName());
        bill.setPrice(amount.multiply(BigDecimal.valueOf(0.05)));
        bill.setUser(user);
        bill.setMoneyTransfer(moneyTransfer);
        billRepository.save(bill);
    }

    @Override
    public List<Bill> getBillList(Principal principal) {
       return billRepository.getBillsByUserId(userService.findByEmail(principal.getName()).getId());
    }
}
