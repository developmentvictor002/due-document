package com.devictor.duedocument.controller.dto;

import com.devictor.duedocument.entity.Document;
import com.devictor.duedocument.entity.User;
import com.devictor.duedocument.entity.enums.DocumentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DocumentRequestDto(
    @NotNull Long userId,
    @NotBlank String title,
    String description,
    @NotNull LocalDate dueDate,
    @NotNull DocumentStatus status
) {
    public Document toDocument(User user) {
        return new Document(
                user,
                title,
                description,
                dueDate,
                status
        );
    }
}
