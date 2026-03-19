package com.heitor.app.service;

import com.heitor.app.entity.User;
import com.heitor.app.enumerate.UserStatus;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    List<User> getAllUsers(String name,
                           String number,
                           String email,
                           LocalDate registrationDate,
                           UserStatus userStatus
    );

    User getUserById(Long id);

    User createUser(User user);

    User partiallyUpdateUser(User newUser, Long id);

    User updateUser(User newUser, Long id);

    void deleteUser(Long id);
}
