package com.heitor.app.controller;

import com.heitor.app.entity.User;
import com.heitor.app.enumerate.UserStatus;
import com.heitor.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String number,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) LocalDate registrationDate,
            @RequestParam(required = false) UserStatus userStatus) {

        List<User> users = userService.getAllUsers(
                name,
                number,
                email,
                registrationDate,
                userStatus
        );

        LOGGER.info("Users successfully fetched: {}", users);

        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) { // pega a variavel da URL (/users/10)
        User user = userService.getUserById(id);

        LOGGER.info("User successfully fetched: {}", user);

        return user;
    }

    @PostMapping
    public User createUser(@RequestBody User user) { // pega o BODY da requisicao
        User savedUser = userService.createUser(user);

        LOGGER.info("User successfully created: {}", savedUser);

        return savedUser;
    }

    @PatchMapping("/{id}")
    public User partiallyUpdateUser(@RequestBody User newUser, @PathVariable Long id) {
        User updatedUser = userService.partiallyUpdateUser(newUser, id);

        LOGGER.info("User partially updated successfully: {}", updatedUser);

        return updatedUser;
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        User updatedUser = userService.updateUser(newUser, id);

        LOGGER.info("Full User successfully updated: {}", updatedUser);

        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        LOGGER.info("User successfully deleted: {}", id);
    }
}
