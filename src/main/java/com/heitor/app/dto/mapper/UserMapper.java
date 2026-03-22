package com.heitor.app.dto.mapper;

import com.heitor.app.dto.request.UserRequestDTO;
import com.heitor.app.dto.response.UserResponseDTO;
import com.heitor.app.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public List<UserResponseDTO> toDtoList(List<User> entities) {
        if (entities.isEmpty()) {
            return null;
        }

        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    // Converte Entity para o que será exposto na API
    public UserResponseDTO toDto(User entity) {
        if (entity == null) {
            return null;
        }

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setNumber(entity.getNumber());
        dto.setEmail(entity.getEmail());
        dto.setRegistrationDate(entity.getRegistrationDate());
        dto.setUserStatus(entity.getUserStatus());

        return dto;
    }

    // Converte o DTO de entrada para uma nova Entity (POST ou PUT)
    public User toEntity(UserRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setName(dto.getName());
        user.setNumber(dto.getNumber());
        user.setEmail(dto.getEmail());
        user.setRegistrationDate(dto.getRegistrationDate());
        user.setUserStatus(dto.getUserStatus());

        return user;
    }

    // Atualiza apenas os campos NÃO-NULL do DTO na Entity existente (EVITAR SOBRESCREVER CAMPOS COM 'NULL')
    public void updateEntityFromDto(UserRequestDTO dto, User user) {
        if (dto == null || user == null) {
            return;
        }

        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        if (dto.getNumber() != null) {
            user.setNumber(dto.getNumber());
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getRegistrationDate() != null) {
            user.setRegistrationDate(dto.getRegistrationDate());
        }

        if (dto.getUserStatus() != null) {
            user.setUserStatus(dto.getUserStatus());
        }
    }
}
