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

    @Test
    public void testSaveUser() throws Exception {
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
    public void testGetUserByEmail() throws Exception {
        User user = new User();
        user.setPassword("test");
        user.setAddress("test");
        user.setEmail("test");
        user.setBirthdate(new Date(10/10/2000));
        user.setFirstName("test");
        user.setLastName("test");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        User result = userRepository.findByEmail(user.getEmail());
        assertThat(result).isNotNull();
    }

    @Test
    public void testGetUserById() throws Exception {
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
}
