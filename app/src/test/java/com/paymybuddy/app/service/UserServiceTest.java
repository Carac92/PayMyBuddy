package com.paymybuddy.app.service;

import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.UserRepository;
import com.paymybuddy.app.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private Principal principal;


    @Test
    void testSaveUser() throws Exception {
        User user = new User();
        user.setPassword("test");
        user.setAddress("test");
        user.setEmail("test");
        user.setBirthdate(new Date(10/10/2000));
        user.setFirstName("test");
        user.setLastName("test");

        when(userRepository.save(any())).thenReturn(user);
        userService.addUser(user);
        verify(userRepository,times(1)).save(any());

    }

    @Test
    void testGetUserByEmail() throws Exception {
        User user = new User();
        user.setPassword("test");
        user.setAddress("test");
        user.setEmail("test");
        user.setBirthdate(new Date(10/10/2000));
        user.setFirstName("test");
        user.setLastName("test");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        User result = userService.findByEmail(user.getEmail());
        assertThat(result.getLastName()).isEqualTo(user.getLastName());
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User();
        user.setPassword("test");
        user.setAddress("test");
        user.setEmail("test");
        user.setBirthdate(new Date(10/10/2000));
        user.setFirstName("test");
        user.setLastName("test");

        when (userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        Optional<User> result = userService.getById((long)1);
        assertThat(result).isNotNull();
    }
    @Test
    void testDeleteUserByUserId() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setPassword("test");
        user.setAddress("test");
        user.setEmail("test");
        user.setBirthdate(new Date(10/10/2000));
        user.setFirstName("test");
        user.setLastName("test");

        userService.deleteConnectedUser(user.getId());
        verify(userRepository,times(1)).deleteById(1L);
    }
    @Test
    void testModificationUserByUserID() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setPassword("test");
        user.setAddress("test");
        user.setEmail("test");
        user.setBirthdate(new Date(10/10/2000));
        user.setFirstName("test");
        user.setLastName("test");
        User modifiedUser = new User();
        modifiedUser.setId(user.getId());
        modifiedUser.setPassword("test123");
        modifiedUser.setAddress(user.getAddress());
        modifiedUser.setEmail(user.getEmail());
        modifiedUser.setBirthdate(user.getBirthdate());
        modifiedUser.setFirstName(user.getFirstName());
        modifiedUser.setLastName(user.getLastName());

        when(userRepository.save(any())).thenReturn(modifiedUser);
        when(principal.getName()).thenReturn("test");
        when(userService.findByEmail(principal.getName())).thenReturn(user);

        boolean result = userService.updateConnectedUser(principal, modifiedUser);

        assertThat(result).isTrue();
    }

    @Test
    void testUpdateCreditForUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setPassword("test");
        user.setAddress("test");
        user.setEmail("test");
        user.setBirthdate(new Date(10/10/2000));
        user.setFirstName("test");
        user.setLastName("test");
        user.setCredit(BigDecimal.valueOf(100.00));

        userService.updateCreditForUser(user);

        when(userRepository.save(any())).thenReturn(user);

        verify(userRepository,times(1)).save(any());
    }
}
