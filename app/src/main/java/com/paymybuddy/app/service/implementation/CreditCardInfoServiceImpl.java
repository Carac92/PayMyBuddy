package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.CreditCardInfo;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.CreditCardInfoRepository;
import com.paymybuddy.app.service.CreditCardInfoService;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Objects;

@Service
public class CreditCardInfoServiceImpl implements CreditCardInfoService {
    @Autowired
    private CreditCardInfoRepository creditCardInfoRepository;
    @Autowired
    private UserService userService;

    @Override
    public CreditCardInfo getCreditCardInfoForUser(Principal principal) {
        return creditCardInfoRepository.getCreditCardInfoByUserId(userService.findByEmail(principal.getName())
                .getId());
    }

    @Override
    public void addCreditCardInfoForUser(CreditCardInfo creditCardInfo, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        creditCardInfo.setUser(user);
        creditCardInfoRepository.save(creditCardInfo);
    }

    @Override
    public boolean deleteCreditCardInfoForUser(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        CreditCardInfo creditCardInfo = creditCardInfoRepository.getCreditCardInfoByUserId(user.getId());
        Long id =creditCardInfoRepository.deleteCreditCardInfoByUserId(user.getId());
        return Objects.equals(id, creditCardInfo.getCreditCardID());
    }
}
