package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.BankInfo;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.BankInfoRepository;
import com.paymybuddy.app.service.BankInfoService;
import com.paymybuddy.app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

/**
 * Implementation of Bank Info service, autowired to Bank Info Repository and User Service.
 * Permits to add a new bank information, delete a bank information by id, get a list of all bank information for the
 * connected user and get a bank info by id.
 */
@Log4j2
@Service
public class BankInfoServiceImpl implements BankInfoService {

    @Autowired
    private BankInfoRepository bankInfoRepository;
    @Autowired
    private UserService userService;

    @Override
    public void addBankInfo(Principal principal, BankInfo bankInfo) {
        log.info("adding bank information for " + principal.getName());
        User user = userService.findByEmail(principal.getName());
        bankInfo.setUser(user);
        bankInfoRepository.save(bankInfo);
    }

    @Override
    public void deleteById(long bankInfoId) {
        log.info("deleting bank information : " + bankInfoId);
        bankInfoRepository.deleteById(bankInfoId);
    }

    @Override
    public List<BankInfo> getBankInfosForUser(Principal principal) {
        log.info("getting all bank informations for " + principal.getName());
        return bankInfoRepository.getBankInfoByUserId(userService.findByEmail(principal.getName()).getId());
    }

    @Override
    public BankInfo getBankInfoById(long bankInfoId) {
        log.info("getting bank information : " + bankInfoId);
        return bankInfoRepository.getBankInfoById(bankInfoId);
    }

}
