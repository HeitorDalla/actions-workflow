package com.heitor.app.service.impl;

import com.heitor.app.dto.request.UserRequestDTO;
import com.heitor.app.dto.response.UserResponseDTO;
import com.heitor.app.entity.User;
import com.heitor.app.enums.UserStatus;
import com.heitor.app.exception.UserNotFoundException;
import com.heitor.app.mapper.UserMapper;
import com.heitor.app.repository.UserRepository;
import com.heitor.app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserResponseDTO> getAllUsers(String name,
                                             String number,
                                             String email,
                                             LocalDate registrationDate,
                                             UserStatus userStatus) {

        List<User> users = userRepository.getAllUsers(name, number, email, registrationDate, userStatus);

        return mapper.toDtoList(users);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return mapper.toDto(user);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = mapper.toEntity(dto);

        User savedUser = userRepository.save(user);

        return mapper.toDto(savedUser);
    }

    @Override
    public UserResponseDTO partiallyUpdateUser(UserRequestDTO dto, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        mapper.updateEntityFromDto(dto, user);

        userRepository.save(user);

        return mapper.toDto(user);
    }

    @Override
    public UserResponseDTO updateUser(UserRequestDTO dto, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // Copiar tudo do DTO
        User newState = mapper.toEntity(dto);
        newState.setId(user.getId());

        userRepository.save(newState);

        return mapper.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(existingUser);
    }
}
