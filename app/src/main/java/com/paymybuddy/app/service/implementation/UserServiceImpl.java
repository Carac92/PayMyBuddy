package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.UserRepository;
import com.paymybuddy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Objects;
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
    public boolean addUser(User user) {
        if(userRepository.findByEmail(user.getEmail())==null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCredit(BigDecimal.ZERO);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean updateConnectedUser(Principal principal, User modifiedUser) {
        User user = userRepository.findByEmail(principal.getName());
        user.setFirstName(modifiedUser.getFirstName());
        user.setLastName(modifiedUser.getLastName());
        user.setBirthdate(modifiedUser.getBirthdate());
        user.setAddress(modifiedUser.getAddress());
        user.setPassword(passwordEncoder.encode(modifiedUser.getPassword()));
        userRepository.save(user);
        return Objects.equals(userRepository.findByEmail(principal.getName()).getFirstName(),modifiedUser.getFirstName());
    }

    @Override
    public void updateCreditForUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteConnectedUser(Long id) {
        userRepository.deleteById(id);
    }

}
