package com.heitor.app.service.impl;

import com.heitor.app.dto.input.UserRequestDTO;
import com.heitor.app.dto.output.LoanResponseDTO;
import com.heitor.app.dto.output.ReservationResponseDTO;
import com.heitor.app.dto.output.UserResponseDTO;
import com.heitor.app.entity.Loan;
import com.heitor.app.entity.Reservation;
import com.heitor.app.entity.User;
import com.heitor.app.enums.RecordStatus;
import com.heitor.app.enums.UserStatus;
import com.heitor.app.exception.BusinessException;
import com.heitor.app.exception.UserNotFoundException;
import com.heitor.app.mapper.LoanMapper;
import com.heitor.app.mapper.ReservationMapper;
import com.heitor.app.mapper.UserMapper;
import com.heitor.app.repository.LoanRepository;
import com.heitor.app.repository.ReservationRepository;
import com.heitor.app.repository.UserRepository;
import com.heitor.app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;
    private final ReservationRepository reservationRepository;
    private final UserMapper mapper;
    private final LoanMapper loanMapper;
    private final ReservationMapper reservationMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           LoanRepository loanRepository,
                           ReservationRepository reservationRepository,
                           UserMapper mapper,
                           LoanMapper loanMapper,
                           ReservationMapper reservationMapper) {
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
        this.reservationRepository = reservationRepository;
        this.mapper = mapper;
        this.loanMapper = loanMapper;
        this.reservationMapper = reservationMapper;
    }

    // Métodos de busca
    @Override
    public List<UserResponseDTO> getAllUsers(String name,
                                             String number,
                                             String email) {

        List<User> users = userRepository.getAllUsers(name, number, email);
        return mapper.toDtoList(users);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return mapper.toDto(user);
    }

    // Métodos de atualizações
    @Transactional
    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = mapper.toEntity(dto);

        // Regras de negócio
        user.setRegistrationDate(LocalDate.now());
        user.setUserStatus(UserStatus.OK);
        user.setRecordStatus(RecordStatus.ACTIVE);

        User savedUser = userRepository.save(user);
        return mapper.toDto(savedUser);
    }

    @Transactional
    @Override
    public UserResponseDTO partiallyUpdateUser(UserRequestDTO dto,
                                               Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        mapper.updateEntityFromDto(dto, user);

        userRepository.save(user);
        return mapper.toDto(user);
    }

    @Transactional
    @Override
    public UserResponseDTO updateUser(UserRequestDTO dto,
                                      Long id) {
        User current = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // Copiar tudo do DTO
        User newState = mapper.toEntity(dto);
        newState.setId(current.getId());

        // Regras de negócio
        newState.setRegistrationDate(current.getRegistrationDate());
        newState.setUserStatus(current.getUserStatus());
        newState.setRecordStatus(current.getRecordStatus());

        User saved = userRepository.save(newState);
        return mapper.toDto(saved);
    }

    @Transactional
    @Override
    public void deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // Verificar se usuário tem empréstimos pedentes
        if (!user.getLoans().isEmpty()) {
            throw new BusinessException("The user cannot be deactivated because they have active loans.");
        }

        // Verificar se usuário tem multas pendentes


        // Verificar se usuário tem reservas pendentes
        if (!user.getReservations().isEmpty()) {
            throw new BusinessException("The user cannot be deactivated because they have active reservations.");
        }

        user.setRecordStatus(RecordStatus.INACTIVE);

        userRepository.save(user);
    }

    // Métodos de Ativação e Desativação
    @Transactional
    @Override
    public void activateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setUserStatus(UserStatus.OK);
        user.setRecordStatus(RecordStatus.ACTIVE);

        userRepository.save(user);
    }

    // Metodos de relacionamentos
    @Override
    public List<LoanResponseDTO> getUserLoans(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        List<Loan> loans = loanRepository.findByUserId(id);

        return loanMapper.toDtoList(loans);
    }

    @Override
    public List<ReservationResponseDTO> getUserReservations(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        List<Reservation> reservations = reservationRepository.findByUserId(id);

        return reservationMapper.toDtoList(reservations);
    }
}
