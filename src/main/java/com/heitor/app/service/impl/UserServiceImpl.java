package com.heitor.app.service.impl;

import com.heitor.app.entity.User;
import com.heitor.app.enumerate.UserStatus;
import com.heitor.app.exception.UserNotFoundException;
import com.heitor.app.repository.UserRepository;
import com.heitor.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers(String name,
                                  String number,
                                  String email,
                                  LocalDate registrationDate,
                                  UserStatus userStatus) {

        return userRepository.getAllUsers(
                name,
                number,
                email,
                registrationDate,
                userStatus
        );
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User newUser, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (newUser.getName() != null) {
            user.setName(newUser.getName());
        }

        if (newUser.getNumber() != null) {
            user.setNumber(newUser.getNumber());
        }

        if (newUser.getEmail() != null) {
            user.setEmail(newUser.getEmail());
        }

        if (newUser.getRegistrationDate() != null) {
            user.setRegistrationDate(newUser.getRegistrationDate());
        }

        if (newUser.getUserStatus() != null) {
            user.setUserStatus(newUser.getUserStatus());
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(existingUser);
    }
}
