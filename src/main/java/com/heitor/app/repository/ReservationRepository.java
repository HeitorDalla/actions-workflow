package com.heitor.app.repository;

import com.heitor.app.entity.Reservation;
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
          AND (:reservationStatus IS NULL OR r.reservationStatus = :reservationStatus)
    """)
    List<Reservation> findAllReservations(
            @Param("userId") Long userId,
            @Param("bookId") Long bookId,
            @Param("reservationStatus") ReservationStatus reservationStatus
    );

    // Encontra reservas de livros feitos pelo usuario buscado
    @Query("""
        SELECT r
        FROM Reservation r
        WHERE r.user.id = :userId
    """)
    List<Reservation> findByUserId(@Param("userId") Long userId);
}