package com.heitor.app.controller;

import com.heitor.app.dto.input.UserRequestDTO;
import com.heitor.app.dto.output.LoanResponseDTO;
import com.heitor.app.dto.output.ReservationResponseDTO;
import com.heitor.app.dto.output.UserResponseDTO;
import com.heitor.app.enums.RecordStatus;
import com.heitor.app.enums.UserStatus;
import com.heitor.app.service.UserService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(required = false) String email) {

        return ResponseEntity.ok(userService.getAllUsers(
                name,
                number,
                email
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> partiallyUpdateUser(@RequestBody UserRequestDTO dto,
                                                               @PathVariable Long id) {
        return ResponseEntity.ok(userService.partiallyUpdateUser(dto, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@Valid @RequestBody UserRequestDTO dto,
                                                      @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateUser(dto, id));
    }

    // Rotas para Ativar e Desativar Usuários
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable Long id) {
        userService.activateUser(id);

        return ResponseEntity.noContent().build();
    }

    // Rotas de relacionamentos
    @GetMapping("/{id}/loans")
    public ResponseEntity<List<LoanResponseDTO>> getUserLoans(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserLoans(id));
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getUserReservations(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserReservations(id));
    }
}
