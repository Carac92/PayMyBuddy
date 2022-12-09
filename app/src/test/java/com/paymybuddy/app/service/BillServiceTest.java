package com.paymybuddy.app.service;

import com.paymybuddy.app.model.Bill;
import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.BillRepository;
import com.paymybuddy.app.service.implementation.BillServiceImpl;
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
public class BillServiceTest {
    @InjectMocks
    private BillServiceImpl billService;
    @Mock
    private BillRepository billRepository;
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

        BigDecimal amount = BigDecimal.valueOf(30.00);

        Bill bill = new Bill();
        bill.setPrice(amount.multiply(BigDecimal.valueOf(0.05)));
        bill.setUser(user);
        List<Bill> billList = new ArrayList<Bill>();
        billList.add(bill);

        when(principal.getName()).thenReturn("test");
        when(userService.findByEmail(principal.getName())).thenReturn(user);
        when(billRepository.getBillsByUserId(1L)).thenReturn(billList);
    }
    @Test
    void testAddBillForUser() throws Exception {
        billService.addBill(principal, BigDecimal.valueOf(30.00));
        verify(billRepository,times(1)).save(any());
    }
    @Test
    void testGetBillListForUser() throws Exception {
        List <Bill> result = billService.getBillList(principal);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getPrice()).isEqualTo(BigDecimal.valueOf(30.00).multiply(BigDecimal.valueOf(0.05)));
    }
}
