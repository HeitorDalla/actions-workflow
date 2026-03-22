package com.heitor.app.service;

import com.heitor.app.dto.request.UserRequestDTO;
import com.heitor.app.dto.response.UserResponseDTO;
import com.heitor.app.enums.UserStatus;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers(String name,
                                      String number,
                                      String email,
                                      LocalDate registrationDate,
                                      UserStatus userStatus);

    UserResponseDTO getUserById(Long id);

    UserResponseDTO createUser(UserRequestDTO user);

    UserResponseDTO partiallyUpdateUser(UserRequestDTO newUser, Long id);

    UserResponseDTO updateUser(UserRequestDTO newUser, Long id);

    void deleteUser(Long id);
}
