package com.paymybuddy.app.service.implementation;

import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.UserRepository;
import com.paymybuddy.app.service.UserService;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Override
    public boolean addUser(User user) {
        log.info("adding a new user");
        if(userRepository.findByEmail(user.getEmail())==null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCredit(BigDecimal.ZERO);
            userRepository.save(user);
            log.info("user added");
            return true;
        }
        log.error("Email address already used for an account");
        return false;
    }

    @Override
    public User findByEmail(String email) {
        log.info("getting user by email");
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getById(Long id) {
        log.info("getting user by id");
        return userRepository.findById(id);
    }

    @Override
    public boolean updateConnectedUser(Principal principal, User modifiedUser) {
        log.info("modifying profile of " + principal.getName());
        User user = userRepository.findByEmail(principal.getName());
        user.setFirstName(modifiedUser.getFirstName());
        user.setLastName(modifiedUser.getLastName());
        user.setBirthdate(modifiedUser.getBirthdate());
        user.setAddress(modifiedUser.getAddress());
        user.setPassword(passwordEncoder.encode(modifiedUser.getPassword()));
        userRepository.save(user);
        log.info("profile modified");
        return Objects.equals(userRepository.findByEmail(principal.getName()).getFirstName(),modifiedUser.getFirstName());
    }

    @Override
    public void updateCreditForUser(User user) {
        log.info("updating credit for user");
        userRepository.save(user);
    }

    @Override
    public void deleteConnectedUser(Long id) {
        log.info("deleting account for id :" + id);
        userRepository.deleteById(id);
    }

}
