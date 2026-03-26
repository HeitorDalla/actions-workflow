package com.heitor.app.dto.output;

import com.heitor.app.enums.ReservationStatus;

import java.time.LocalDate;

public class ReservationResponseDTO {
    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDate reservationDate;
    private ReservationStatus reservationStatus;

    public ReservationResponseDTO() {}

    public ReservationResponseDTO(Long id,
                                  Long userId,
                                  Long bookId,
                                  LocalDate reservationDate,
                                  ReservationStatus reservationStatus) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.reservationDate = reservationDate;
        this.reservationStatus = reservationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
