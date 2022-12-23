package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.AccountTransfer;
import com.paymybuddy.app.model.BankInfo;
import com.paymybuddy.app.model.MoneyTransfer;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.AccountTransferRepository;
import com.paymybuddy.app.service.AccountTransferService;
import com.paymybuddy.app.service.BankInfoService;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class AccountTransferServiceImpl implements AccountTransferService {
    @Autowired
    private UserService userService;
    @Autowired
    private BankInfoService bankInfoService;
    @Autowired
    private AccountTransferRepository accountTransferRepository;

    @Override
    public void addAccountTransfer(Principal principal, BigDecimal Amount, Long bankInfoId, Boolean deposit) {
        User connectedUser = userService.findByEmail(principal.getName());
        AccountTransfer accountTransfer = new AccountTransfer();
        accountTransfer.setUser(connectedUser);
        if(deposit){
            accountTransfer.setAmount(Amount);
            connectedUser.setCredit(connectedUser.getCredit().add(Amount));
            userService.updateCreditForUser(connectedUser);
        }
        else{
            accountTransfer.setAmount(Amount.negate());
            connectedUser.setCredit(connectedUser.getCredit().subtract(Amount));
            userService.updateCreditForUser(connectedUser);
        }
        accountTransfer.setTransferDate(Date.valueOf(LocalDate.now()));
        accountTransfer.setBankInfo(bankInfoService.getBankInfoById(bankInfoId));
        accountTransferRepository.save(accountTransfer);
    }

    @Override
    public List<AccountTransfer> getAccountTransfers(Principal principal) {
        return accountTransferRepository.getAccountTransferByUserId(userService.findByEmail(principal.getName())
                .getId());
    }
}
