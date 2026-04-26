package com.heitor.app.service;

import com.heitor.app.dto.input.UserPatchDTO;
import com.heitor.app.dto.input.UserUpsertDTO;
import com.heitor.app.dto.output.LoanResponseDTO;
import com.heitor.app.dto.output.ReservationResponseDTO;
import com.heitor.app.dto.output.UserResponseDTO;
import com.heitor.app.enums.RecordStatus;
import com.heitor.app.enums.UserStatus;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers(String name,
                                      String number,
                                      String email,
                                      UserStatus userStatus,
                                      RecordStatus recordStatus);

    UserResponseDTO getUserById(Long id);

    UserResponseDTO createUser(UserUpsertDTO dto);

    UserResponseDTO partiallyUpdateUser(UserPatchDTO dto,
                                        Long id);

    UserResponseDTO updateUser(UserUpsertDTO dto,
                               Long id);

    void activateUser(Long id);

    void deactivateUser(Long id);

    List<LoanResponseDTO> getUserLoans(Long id);

    List<ReservationResponseDTO> getUserReservations(Long id);
}
