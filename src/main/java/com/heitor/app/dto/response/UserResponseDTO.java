package com.heitor.app.dto.response;

import com.heitor.app.enumerate.UserStatus;

import java.time.LocalDate;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String number;
    private String email;
    private LocalDate registrationDate;
    private UserStatus userStatus;

    public UserResponseDTO() {}

    public UserResponseDTO(Long id,
                           String name,
                           String number,
                           String email,
                           LocalDate registrationDate,
                           UserStatus userStatus) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.registrationDate = registrationDate;
        this.userStatus = userStatus;
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
}
