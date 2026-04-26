package com.heitor.app.dto.input;

import jakarta.validation.constraints.Email;

public class UserPatchDTO {
    private String name;
    private String number;

    @Email
    private String email;

    public UserPatchDTO() {}

    public UserPatchDTO(String name,
                         String number,
                         String email) {
        this.name = name;
        this.number = number;
        this.email = email;
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
}
