package com.paymybuddy.app.service;

import com.paymybuddy.app.model.User;

import java.security.Principal;
import java.util.Optional;

/**
 * Interface of UserService.
 */
public interface UserService {
    void addUser(User user);
    User findByEmail(String email);
    Optional<User> getById(Long id);
    void updateUserById(Principal principal, User modifiedUser);
}
