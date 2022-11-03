package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.UserRepository;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Implementation of UserService with addUser that encode password and findByEmail + findByID
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCredit(BigDecimal.ZERO);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
}
