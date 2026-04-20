package com.heitor.app.service.impl;

import com.heitor.app.dto.input.ReservationRequestDTO;
import com.heitor.app.dto.output.ReservationResponseDTO;
import com.heitor.app.entity.Book;
import com.heitor.app.entity.Reservation;
import com.heitor.app.entity.User;
import com.heitor.app.enums.*;
import com.heitor.app.exception.*;
import com.heitor.app.mapper.ReservationMapper;
import com.heitor.app.repository.*;
import com.heitor.app.service.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final FineRepository fineRepository;
    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  UserRepository userRepository,
                                  BookRepository bookRepository,
                                  FineRepository fineRepository,
                                  ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.fineRepository = fineRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public List<ReservationResponseDTO> getAllReservations(Long userId,
                                                           Long bookId,
                                                           ReservationStatus reservationStatus,
                                                           RecordStatus recordStatus) {

        List<Reservation> reservations = reservationRepository.findAllReservations(userId, bookId, reservationStatus, recordStatus);

        return reservationMapper.toDtoList(reservations);
    }

    @Override
    public ReservationResponseDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReserveNotFoundException(id));

        return reservationMapper.toDto(reservation);
    }

    @Transactional
    @Override
    public ReservationResponseDTO createReservation(ReservationRequestDTO dto) {
        // Verificar usuario
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(dto.getUserId()));
        if (user.getUserStatus() != UserStatus.OK || user.getRecordStatus() != RecordStatus.ACTIVE) {
            throw new BusinessException("User is not allowed to create reservations.");
        }

        // Verificar se usuário tem multa pendente
        if (fineRepository.existsByLoanUserAndFineStatus(user, FineStatus.OPEN)) {
            throw new BusinessException("User has an outstanding fine.");
        }

        // Verificar livro
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new BookNotFoundException(dto.getBookId()));
        if (book.getBookStatus() != BookStatus.AVAILABLE) {
            throw new BusinessException("Book is unavailable for reservation.");
        }

        if (book.getRecordStatus() != RecordStatus.ACTIVE) {
            throw new BusinessException("Book is inactive.");
        }

        if (book.getAvailableQuantity() == null || book.getAvailableQuantity() <= 0) {
            throw new BusinessException("No copies available for reservation.");
        }

        // Diminuir a quantidade disponivel e o status do livro conforme a quantidade disponivel


        // Impedir reserva duplicada do livro pelo mesmo usuário
        boolean exists = reservationRepository.existsByUserAndBookAndReservationStatus(user, book, List.of(ReservationStatus.PENDING, ReservationStatus.CONFIRMED));
        if (exists) {
            throw new BusinessException("User already has a reservation for this book");
        }

        Reservation reservation = reservationMapper.toEntity(dto, user, book);

        reservation.setReservationDate(LocalDate.now());
        reservation.setReservationStatus(ReservationStatus.PENDING);
        reservation.setRecordStatus(RecordStatus.ACTIVE);

        reservationRepository.save(reservation);
        return reservationMapper.toDto(reservation);
    }
}
