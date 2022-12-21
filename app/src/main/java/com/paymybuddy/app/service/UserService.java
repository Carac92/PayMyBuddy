package com.paymybuddy.app.service;

import com.paymybuddy.app.model.User;

import java.security.Principal;
import java.util.Optional;

/**
 * Interface of UserService.
 */
public interface UserService {
    boolean addUser(User user);
    User findByEmail(String email);
    Optional<User> getById(Long id);
    boolean updateConnectedUser(Principal principal, User modifiedUser);
    void updateCreditForUser(User user);
    void deleteConnectedUser(Long id);
}
