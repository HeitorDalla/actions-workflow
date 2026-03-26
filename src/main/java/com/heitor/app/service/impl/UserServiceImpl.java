package com.heitor.app.service.impl;

import com.heitor.app.dto.input.UserRequestDTO;
import com.heitor.app.dto.output.UserResponseDTO;
import com.heitor.app.entity.User;
import com.heitor.app.enums.UserStatus;
import com.heitor.app.exception.UserNotFoundException;
import com.heitor.app.mapper.UserMapper;
import com.heitor.app.repository.UserRepository;
import com.heitor.app.service.UserService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    // Métodos de busca
    @Override
    public List<UserResponseDTO> getAllUsers(String name,
                                             String number,
                                             String email,
                                             UserStatus userStatus) {

        List<User> users = userRepository.getAllUsers(name, number, email, userStatus);
        return mapper.toDtoList(users);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return mapper.toDto(user);
    }

    // Métodos de atualizações
    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = mapper.toEntity(dto);

        // Regras de negócio
        user.setRegistrationDate(LocalDate.now());
        user.setUserStatus(UserStatus.ACTIVE);

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
        User current = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // Copiar tudo do DTO
        User newState = mapper.toEntity(dto);
        newState.setId(current.getId());

        // Regras de negócio
        newState.setRegistrationDate(current.getRegistrationDate());
        newState.setUserStatus(current.getUserStatus());

        User saved = userRepository.save(newState);
        return mapper.toDto(saved);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(existingUser);
    }

    // Métodos de Ativação e Desativação
    @Override
    public void activateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public void deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (!user.getLoans().isEmpty()) {
            throw new RuntimeException("The user cannot be deactivated because they have active loans.");
        }

        user.setUserStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }
}
