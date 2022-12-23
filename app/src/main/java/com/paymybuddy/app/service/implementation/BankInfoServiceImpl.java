package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.BankInfo;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.BankInfoRepository;
import com.paymybuddy.app.service.BankInfoService;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
public class BankInfoServiceImpl implements BankInfoService {

    @Autowired
    private BankInfoRepository bankInfoRepository;
    @Autowired
    private UserService userService;

    @Override
    public void addBankInfo(Principal principal, BankInfo bankInfo) {
        User user = userService.findByEmail(principal.getName());
        bankInfo.setUser(user);
        bankInfoRepository.save(bankInfo);
    }

    @Override
    public void deleteById(long bankInfoId) {
        bankInfoRepository.deleteById(bankInfoId);
    }

    @Override
    public List<BankInfo> getBankInfosForUser(Principal principal) {
        return bankInfoRepository.getBankInfoByUserId(userService.findByEmail(principal.getName()).getId());
    }

    @Override
    public BankInfo getBankInfoById(long bankInfoId) {
        return bankInfoRepository.getBankInfoById(bankInfoId);
    }

}
