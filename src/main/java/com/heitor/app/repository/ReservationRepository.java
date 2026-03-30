package com.heitor.app.repository;

import com.heitor.app.entity.Reservation;
import com.heitor.app.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
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
}
