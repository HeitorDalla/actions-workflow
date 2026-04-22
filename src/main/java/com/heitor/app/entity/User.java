package com.heitor.app.entity;

import com.heitor.app.enums.RecordStatus;
import com.heitor.app.enums.UserStatus;
import com.heitor.app.exception.BusinessException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
// @Cacheable  // anotacao para a implementacao do cache nivel 2 do hibernate
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String name;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_number", nullable = false)
    private String number;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_registration_date", nullable = false)
    private LocalDate registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_record_status", nullable = false)
    private RecordStatus recordStatus;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Loan> loans = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    public User() {}

    public User(Long id,
                String name,
                String password,
                String number,
                String email,
                LocalDate registrationDate,
                UserStatus userStatus,
                RecordStatus recordStatus,
                List<Loan> loans,
                List<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.number = number;
        this.email = email;
        this.registrationDate = registrationDate;
        this.userStatus = userStatus;
        this.recordStatus = recordStatus;
        this.loans = loans;
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void activate() {
        this.userStatus = UserStatus.OK;
        this.recordStatus = RecordStatus.ACTIVE;
    }

    public void deactivate() {
        this.recordStatus = RecordStatus.INACTIVE;
    }

    public void initialize() {
        if (this.id != null) {
            throw new BusinessException("User already initialized.");
        }

        this.registrationDate = LocalDate.now();
        activate();
    }
}