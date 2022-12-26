package com.paymybuddy.app.service;

import com.paymybuddy.app.model.AccountTransfer;
import com.paymybuddy.app.model.BankInfo;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.AccountTransferRepository;
import com.paymybuddy.app.service.implementation.AccountTransferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AccountTransferServiceTest {

    @InjectMocks
    private AccountTransferServiceImpl accountTransferService;
    @Mock
    private AccountTransferRepository accountTransferRepository;
    @Mock
    private UserService userService;
    @Mock
    private BankInfoService bankInfoService;
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

        AccountTransfer accountTransfer = new AccountTransfer();
        accountTransfer.setBankInfo(bankInfo);
        accountTransfer.setTransferDate(Date.valueOf(LocalDate.now()));
        accountTransfer.setAmount(BigDecimal.valueOf(10.00));
        accountTransfer.setUser(user);
        accountTransfer.setId(1L);
        List<AccountTransfer> accountTransferList = new ArrayList<>();
        accountTransferList.add(accountTransfer);

        when(principal.getName()).thenReturn("test");
        when(userService.findByEmail(principal.getName())).thenReturn(user);
        when(bankInfoService.getBankInfoById(anyLong())).thenReturn(bankInfo);
        when(accountTransferRepository.getAccountTransferByUserId(anyLong())).thenReturn(accountTransferList);

    }

    @Test
    public void addTransferAccountTest(){
        accountTransferService.addAccountTransfer(principal, BigDecimal.valueOf(10.00), 1L, true);
        verify(accountTransferRepository, times(1)).save(any());
    }
    @Test
    public void getTransferAccountListTest(){
        List<AccountTransfer> result = accountTransferService.getAccountTransfers(principal);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(0).getAmount()).isEqualTo(BigDecimal.valueOf(10.00));
    }
}
