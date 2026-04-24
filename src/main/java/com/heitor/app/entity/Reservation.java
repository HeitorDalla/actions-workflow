package com.heitor.app.entity;

import com.heitor.app.enums.RecordStatus;
import com.heitor.app.enums.ReservationStatus;
import com.heitor.app.exception.BusinessException;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name = "reservation_due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "reservation_return_date")
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_status", nullable = false)
    private ReservationStatus reservationStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_record_status", nullable = false)
    private RecordStatus recordStatus;

    public Reservation() {}

    public Reservation(Long id,
                       User user,
                       Book book,
                       LocalDate reservationDate,
                       LocalDate dueDate,
                       LocalDate returnDate,
                       ReservationStatus reservationStatus,
                       RecordStatus recordStatus) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.reservationDate = reservationDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.reservationStatus = reservationStatus;
        this.recordStatus = recordStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public void initialize() {
        reservationDate = LocalDate.now();
        dueDate = reservationDate.plusDays(2);
        reservationStatus = ReservationStatus.PENDING;
        recordStatus = RecordStatus.ACTIVE;
    }

    public void confirm() {
        if (reservationStatus != ReservationStatus.PENDING) {
            throw new BusinessException("Only pending reservations can be confirmed.");
        }

        reservationStatus = ReservationStatus.CONFIRMED;
    }

    public void expire() {
        if (reservationStatus != ReservationStatus.PENDING) {
            throw new BusinessException("Only pending reservations can expire.");
        }

        reservationStatus = ReservationStatus.EXPIRED;
        recordStatus = RecordStatus.INACTIVE;
    }

    public void finish() {
        if (reservationStatus != ReservationStatus.CONFIRMED) {
            throw new BusinessException("Only confirmed reservations can be returned.");
        }

        returnDate = LocalDate.now();
        reservationStatus = ReservationStatus.RETURNED;
        recordStatus = RecordStatus.INACTIVE;
    }

    public void cancel() {
        if (reservationStatus == ReservationStatus.CANCELLED) {
            throw new BusinessException("Reservation already cancelled.");
        }
        if (reservationStatus == ReservationStatus.EXPIRED) {
            throw new BusinessException("Expired reservations cannot be cancelled.");
        }

        this.reservationStatus = ReservationStatus.CANCELLED;
        this.recordStatus = RecordStatus.INACTIVE;
    }

    public boolean holdsBook() {
        return reservationStatus == ReservationStatus.CONFIRMED;
    }
}