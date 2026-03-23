package com.heitor.app.service;

import com.heitor.app.dto.request.UserRequestDTO;
import com.heitor.app.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers(String name,
                                      String number,
                                      String email);

    UserResponseDTO getUserById(Long id);

    UserResponseDTO createUser(UserRequestDTO user);

    UserResponseDTO partiallyUpdateUser(UserRequestDTO newUser, Long id);

    UserResponseDTO updateUser(UserRequestDTO newUser, Long id);

    void deleteUser(Long id);
}
