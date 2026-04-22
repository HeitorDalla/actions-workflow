package com.heitor.app.service.impl;

import com.heitor.app.dto.input.UserRequestDTO;
import com.heitor.app.dto.output.LoanResponseDTO;
import com.heitor.app.dto.output.ReservationResponseDTO;
import com.heitor.app.dto.output.UserResponseDTO;
import com.heitor.app.entity.Loan;
import com.heitor.app.entity.Reservation;
import com.heitor.app.entity.User;
import com.heitor.app.enums.*;
import com.heitor.app.exception.BusinessException;
import com.heitor.app.exception.UserNotFoundException;
import com.heitor.app.mapper.LoanMapper;
import com.heitor.app.mapper.ReservationMapper;
import com.heitor.app.mapper.UserMapper;
import com.heitor.app.repository.FineRepository;
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
    private final FineRepository fineRepository;
    private final UserMapper mapper;
    private final LoanMapper loanMapper;
    private final ReservationMapper reservationMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           LoanRepository loanRepository,
                           ReservationRepository reservationRepository,
                           FineRepository fineRepository,
                           UserMapper mapper,
                           LoanMapper loanMapper,
                           ReservationMapper reservationMapper) {
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
        this.reservationRepository = reservationRepository;
        this.fineRepository = fineRepository;
        this.mapper = mapper;
        this.loanMapper = loanMapper;
        this.reservationMapper = reservationMapper;
    }

    private List<User> findAllUsers(String name,
                                    String number,
                                    String email,
                                    UserStatus userStatus,
                                    RecordStatus recordStatus) {
        return userRepository.getAllUsers(name, number, email, userStatus, recordStatus);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private void validateUserCanBeDeactivated(User user) {
        // Verificar se usuário tem empréstimos pedentes
        if (loanRepository.existsByUserAndLoanStatus(user, LoanStatus.OVERDUE)) {
            throw new BusinessException("The user cannot be deactivated because they have active loans.");
        }

        // Verificar se usuário tem multas pendentes
        if (fineRepository.existsByLoanUserAndFineStatus(user, FineStatus.OPEN)) {
            throw new BusinessException("The user cannot be deactivated because they have outstanding fines.");
        }

        // Verificar se usuário tem reservas pendentes
        if (reservationRepository.existsByUserAndReservationStatus(user, ReservationStatus.PENDING)) {
            throw new BusinessException("The user cannot be deactivated because they have active reservations.");
        }
    }

    // Métodos de busca
    @Override
    public List<UserResponseDTO> getAllUsers(String name,
                                             String number,
                                             String email,
                                             UserStatus userStatus,
                                             RecordStatus recordStatus) {

        List<User> users = findAllUsers(name, number, email, userStatus, recordStatus);
        return mapper.toDtoList(users);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = findUser(id);

        return mapper.toDto(user);
    }

    @Transactional
    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = mapper.toEntity(dto);

        user.initialize();

        User savedUser = userRepository.save(user);
        return mapper.toDto(savedUser);
    }

    // Métodos de atualizações
    @Transactional
    @Override
    public UserResponseDTO partiallyUpdateUser(UserRequestDTO dto,
                                               Long id) {
        User user = findUser(id);

        mapper.updateEntityFromDto(dto, user);

        userRepository.save(user);
        return mapper.toDto(user);
    }

    @Transactional
    @Override
    public UserResponseDTO updateUser(UserRequestDTO dto,
                                      Long id) {
        User current = findUser(id);

        // Cria um novo estado para o usuario com as novas informações que o cliente passou + as informações de regras de negócio
        User newStateUSer = mapper.toEntity(dto);
        newStateUSer.setId(current.getId());
        newStateUSer.setRegistrationDate(current.getRegistrationDate());
        newStateUSer.setUserStatus(current.getUserStatus());
        newStateUSer.setRecordStatus(current.getRecordStatus());

        User saved = userRepository.save(newStateUSer);
        return mapper.toDto(saved);
    }

    // Métodos de Ativação e Desativação
    @Transactional
    @Override
    public void activateUser(Long id) {
        User user = findUser(id);

        user.activate();

        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deactivateUser(Long id) {
        User user = findUser(id);

        validateUserCanBeDeactivated(user);

        user.deactivate();

        userRepository.save(user);
    }

    // Metodos de relacionamentos
    @Override
    public List<LoanResponseDTO> getUserLoans(Long id) {
        findUser(id);

        List<Loan> loans = loanRepository.findByUserId(id);

        return loanMapper.toDtoList(loans);
    }

    @Override
    public List<ReservationResponseDTO> getUserReservations(Long id) {
        findUser(id);

        List<Reservation> reservations = reservationRepository.findByUserId(id);

        return reservationMapper.toDtoList(reservations);
    }
}
