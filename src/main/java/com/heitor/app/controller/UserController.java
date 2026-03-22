package com.heitor.app.controller;

import com.heitor.app.dto.request.UserRequestDTO;
import com.heitor.app.dto.response.UserResponseDTO;
import com.heitor.app.enumerate.UserStatus;
import com.heitor.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String number,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) LocalDate registrationDate,
            @RequestParam(required = false) UserStatus userStatus) {

        return ResponseEntity.ok(userService.getAllUsers(
                name,
                number,
                email,
                registrationDate,
                userStatus
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> partiallyUpdateUser(@RequestBody UserRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(userService.partiallyUpdateUser(dto, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateUser(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
