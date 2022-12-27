package com.paymybuddy.app.service;

import com.paymybuddy.app.model.BankInfo;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.BankInfoRepository;
import com.paymybuddy.app.service.implementation.BankInfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class BankInfoServiceTest {
    @InjectMocks
    private BankInfoServiceImpl bankInfoService;

    @Mock
    private BankInfoRepository bankInfoRepository;
    @Mock
    private UserService userService;
    @Mock
    private Principal principal;

    @BeforeEach
    public void setUp() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setPassword("test");
        user.setAddress("test");
        user.setEmail("test");
        user.setBirthdate(new Date(10/10/2000));
        user.setFirstName("test");
        user.setLastName("test");
        user.setCredit(BigDecimal.valueOf(100.00));

        BankInfo bankInfo = new BankInfo();
        bankInfo.setUser(user);
        bankInfo.setIban("12345");
        bankInfo.setId(1L);
        bankInfo.setBic("1234");
        bankInfo.setNameOfAccount("test");
        List<BankInfo> bankInfoList = new ArrayList<>();
        bankInfoList.add(bankInfo);

        when(principal.getName()).thenReturn("test");
        when(userService.findByEmail(principal.getName())).thenReturn(user);
        when(bankInfoRepository.getBankInfoByUserId(user.getId())).thenReturn(bankInfoList);

    }

    @Test
    void testAddBankInfoForUser() throws Exception {
        BankInfo bankInfo = new BankInfo();
        bankInfoService.addBankInfo(principal,bankInfo);
        verify(bankInfoRepository, times(1)).save(bankInfo);
    }
    @Test
    void testRemoveBankInfoForUser() throws Exception {
        bankInfoService.deleteById(1L);
        verify(bankInfoRepository, times(1)).deleteById(1L);
    }
    @Test
    void testGetBankInfoForUser() throws Exception {
        List<BankInfo> result = bankInfoService.getBankInfosForUser(principal);
        assertThat(result.get(0).getUser().getLastName()).isEqualTo("test");
        assertThat(result.get(0).getNameOfAccount()).isEqualTo("test");
    }
}
