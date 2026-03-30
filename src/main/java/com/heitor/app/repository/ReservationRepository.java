package com.heitor.app.repository;

import com.heitor.app.entity.Book;
import com.heitor.app.entity.Reservation;
import com.heitor.app.entity.User;
import com.heitor.app.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Busca reservas, aplicando filtros quando os parametros forem informados
    @Query("""
        SELECT r
        FROM Reservation r
        WHERE (:userId IS NULL OR r.user.id = :userId)
          AND (:bookId IS NULL OR r.book.id = :bookId)
          AND (:reservationStatus IS NULL OR LOWER(r.reservationStatus) = :reservationStatus)
    """)
    List<Reservation> findAllReservations(Long userId,
                                          Long bookId,
                                          ReservationStatus reservationStatus);

    List<Reservation> findByUserId(Long id);

    @Query("""
        SELECT COUNT(r) > 0
        FROM Reservation r
        WHERE r.user = :user
            AND r.book = :book
            AND r.reservationStatus IN :status
    """)
    boolean existsByUserAndBookAndReservationStatus(
            @Param("user") User user,
            @Param("book") Book book,
            @Param("status") List<ReservationStatus> status);
}
