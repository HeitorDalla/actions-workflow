package com.heitor.app.service;

import com.heitor.app.dto.input.UserRequestDTO;
import com.heitor.app.dto.output.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers(String name,
                                      String number,
                                      String email);

    UserResponseDTO getUserById(Long id);

    UserResponseDTO createUser(UserRequestDTO dto);

    UserResponseDTO partiallyUpdateUser(UserRequestDTO dto, Long id);

    UserResponseDTO updateUser(UserRequestDTO dto, Long id);

    void deleteUser(Long id);
}
