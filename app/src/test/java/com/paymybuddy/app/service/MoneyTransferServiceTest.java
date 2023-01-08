package com.paymybuddy.app.service;

import com.paymybuddy.app.model.Contact;
import com.paymybuddy.app.model.MoneyTransfer;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.MoneyTransferRepository;
import com.paymybuddy.app.service.implementation.MoneyTransferServiceImpl;
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
public class MoneyTransferServiceTest {
    @InjectMocks
    private MoneyTransferServiceImpl moneyTransferService;

    @Mock
    private MoneyTransferRepository moneyTransferRepository;
    @Mock
    private Principal principal;
    @Mock
    private ContactService contactService;
    @Mock
    private UserService userService;
    @Mock
    private BillService billService;


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

        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("test2");
        user2.setFirstName("test2");
        user2.setLastName("test2");
        user2.setCredit(BigDecimal.valueOf(100.00));

        Contact contact = new Contact();
        contact.setEmail(user2.getEmail());
        contact.setFirstName(user2.getFirstName());
        contact.setLastName(user2.getLastName());
        contact.setUser(user);
        contact.setId(1L);

        List <MoneyTransfer> moneyTransfers = new ArrayList<MoneyTransfer>();
        MoneyTransfer moneyTransfer = new MoneyTransfer (BigDecimal.valueOf(30.00), Date.valueOf(LocalDate.now()),user, contact);
        moneyTransfers.add(moneyTransfer);


        when(moneyTransferRepository.getMoneyTransferByUserId(anyLong())).thenReturn(moneyTransfers);
        when(userService.findByEmail("test")).thenReturn(user);
        when(contactService.getContactById(1L)).thenReturn(contact);
        when(userService.findByEmail("test2")).thenReturn(user2);
        when(principal.getName()).thenReturn("test");
    }

    @Test
    public void testMoneyTransfer() throws Exception {

        moneyTransferService.addMoneyTransfer(principal, 1L, BigDecimal.valueOf(30.00), "description");
        verify(moneyTransferRepository, times(1)).save(any());
    }

    @Test
    public void testGetMoneyTransfersForUser() throws Exception {
        List<MoneyTransfer> result = moneyTransferService.getMoneyTransfers(principal);
        verify(moneyTransferRepository, times(1)).getMoneyTransferByUserId(anyLong());
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getAmount()).isEqualTo(BigDecimal.valueOf(30.00));
    }
}
