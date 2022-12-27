package com.paymybuddy.app.service;

import com.paymybuddy.app.model.User;
import com.paymybuddy.app.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public boolean addUser(User user) {
        log.info("adding a new user");
        if(userRepository.findByEmail(user.getEmail())==null) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setCredit(BigDecimal.ZERO);
            userRepository.save(user);
            log.info("user added");
            return true;
        }
        log.error("Email address already used for an account");
        return false;
    }


    public User findByEmail(String email) {
        log.info("getting user by email");
        return userRepository.findByEmail(email);
    }


    public Optional<User> getById(Long id) {
        log.info("getting user by id");
        return userRepository.findById(id);
    }


    public boolean updateConnectedUser(Principal principal, User modifiedUser) {
        log.info("modifying profile of " + principal.getName());
        User user = userRepository.findByEmail(principal.getName());
        user.setFirstName(modifiedUser.getFirstName());
        user.setLastName(modifiedUser.getLastName());
        user.setBirthdate(modifiedUser.getBirthdate());
        user.setAddress(modifiedUser.getAddress());
        user.setPassword(new BCryptPasswordEncoder().encode(modifiedUser.getPassword()));
        userRepository.save(user);
        log.info("profile modified");
        return Objects.equals(userRepository.findByEmail(principal.getName()).getFirstName(),modifiedUser.getFirstName());
    }


    public void updateCreditForUser(User user) {
        log.info("updating credit for user");
        userRepository.save(user);
    }


    public void deleteConnectedUser(Long id) {
        log.info("deleting account for id :" + id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return user;
    }
}
