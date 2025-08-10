package com.devictor.duedocument.controller.dto;

import com.devictor.duedocument.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank String fullName,
        @Email @NotBlank String email,
        @NotBlank @Size(min = 8) String password
) {

    public User toUser() {
        return new User(
                fullName,
                email,
                password
        );
    }
}
