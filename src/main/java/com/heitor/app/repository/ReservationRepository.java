package com.heitor.app.repository;

import com.heitor.app.entity.Reservation;
import com.heitor.app.enums.RecordStatus;
import com.heitor.app.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Busca reservas, aplicando filtros quando os parametros forem informados
    @Query("""
        SELECT r
        FROM Reservation r
        WHERE (:userId IS NULL OR r.user.id = :userId)
          AND (:bookId IS NULL OR r.book.id = :bookId)
          AND (:reservationStatus IS NULL OR r.reservationStatus = :reservationStatus)
          AND (:recordStatus IS NULL OR r.recordStatus = :recordStatus)
    """)
    List<Reservation> findAllReservations(
            @Param("userId") Long userId,
            @Param("bookId") Long bookId,
            @Param("reservationStatus") ReservationStatus reservationStatus,
            @Param("recordStatus") RecordStatus recordStatus
    );

    // Encontra reservas de livros feitos pelo usuario buscado
    @Query("""
        SELECT r
        FROM Reservation r
        WHERE r.user.id = :userId
    """)
    List<Reservation> findByUserId(@Param("userId") Long userId);
}