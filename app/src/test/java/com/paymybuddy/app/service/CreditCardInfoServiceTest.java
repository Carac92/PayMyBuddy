package com.paymybuddy.app.service;

import com.paymybuddy.app.model.CreditCardInfo;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.CreditCardInfoRepository;
import com.paymybuddy.app.service.implementation.CreditCardInfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CreditCardInfoServiceTest {

    @InjectMocks
    private CreditCardInfoServiceImpl creditCardInfoService;

    @Mock
    private CreditCardInfoRepository creditCardInfoRepository;
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

        CreditCardInfo creditCardInfo = new CreditCardInfo();
        creditCardInfo.setUser(user);
        creditCardInfo.setCreditCardCCV(334);
        creditCardInfo.setCreditCardID(1L);
        creditCardInfo.setCreditCardName("test");
        creditCardInfo.setCreditCardNumber("12345567");
        creditCardInfo.setCreditCardExpirationDate(new Date(20/10/18));

        when(creditCardInfoRepository.getCreditCardInfoByUserId(any())).thenReturn(creditCardInfo);
        when(creditCardInfoRepository.deleteCreditCardInfoByUserId(any())).thenReturn(1L);
        when(principal.getName()).thenReturn("test");
        when(userService.findByEmail(any())).thenReturn(user);

    }
    @Test
    void testGetCreditCardInfoForUser() throws Exception {
        CreditCardInfo result = creditCardInfoService.getCreditCardInfoForUser(principal);
        assertThat(result.getUser().getLastName()).isEqualTo("test");
        assertThat(result.getCreditCardName()).isEqualTo("test");
    }
    @Test
    void testAddCreditCardInfoForUser() throws Exception {
        CreditCardInfo creditCardInfo = new CreditCardInfo();
        creditCardInfo.setCreditCardCCV(334);
        creditCardInfo.setCreditCardID(1L);
        creditCardInfo.setCreditCardName("test");
        creditCardInfo.setCreditCardNumber("12345567");
        creditCardInfo.setCreditCardExpirationDate(new Date(20/10/18));
        creditCardInfoService.addCreditCardInfoForUser(creditCardInfo, principal);
        verify(creditCardInfoRepository, times(1)).save(creditCardInfo);
    }
    @Test
    void testDeleteCreditCardInfoForUser () throws Exception {
        boolean result = creditCardInfoService.deleteCreditCardInfoForUser(principal);
        assertThat(result).isTrue();
    }

}
