package com.paymybuddy.app.service;

import com.paymybuddy.app.model.User;

import java.util.Optional;

/**
 * Interface of UserService.
 */
public interface UserService {
    void addUser(User user);
    User findByEmail(String email);
    Optional<User> getById(Long id);
    void updateUserById(Long id, User modifiedUser);
}
