package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.AccountTransfer;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.AccountTransferRepository;
import com.paymybuddy.app.service.AccountTransferService;
import com.paymybuddy.app.service.BankInfoService;
import com.paymybuddy.app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@Service
public class AccountTransferServiceImpl implements AccountTransferService {
    @Autowired
    private UserService userService;
    @Autowired
    private BankInfoService bankInfoService;
    @Autowired
    private AccountTransferRepository accountTransferRepository;

    @Override
    public void addAccountTransfer(Principal principal, BigDecimal amount, Long bankInfoId, Boolean deposit) {
        User connectedUser = userService.findByEmail(principal.getName());
        log.info("Requiring a transfer for " + connectedUser.getEmail());
        AccountTransfer accountTransfer = new AccountTransfer();
        accountTransfer.setUser(connectedUser);
        accountTransfer.setTransferDate(Date.valueOf(LocalDate.now()));
        accountTransfer.setBankInfo(bankInfoService.getBankInfoById(bankInfoId));
        if(deposit){
            log.info("Transferring " + amount.toString() + "€ from bank account");
            accountTransfer.setAmount(amount);
            connectedUser.setCredit(connectedUser.getCredit().add(amount));
            userService.updateCreditForUser(connectedUser);
            accountTransferRepository.save(accountTransfer);
        }
        else{
            if(connectedUser.getCredit().doubleValue()>=amount.doubleValue()){
                log.info("Transferring " + amount.toString() + "€ to bank account");
                accountTransfer.setAmount(amount.negate());
                connectedUser.setCredit(connectedUser.getCredit().subtract(amount));
                userService.updateCreditForUser(connectedUser);
                accountTransferRepository.save(accountTransfer);
            }
            log.error("Not enough credit to transfer to bank account");
        }
    }

    @Override
    public List<AccountTransfer> getAccountTransfers(Principal principal) {
        log.info("Getting all transfer from/to bank account for " + principal.getName());
        return accountTransferRepository.getAccountTransferByUserId(userService.findByEmail(principal.getName())
                .getId());
    }
}
