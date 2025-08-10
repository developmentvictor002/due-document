package com.devictor.duedocument.service.dto;

import com.devictor.duedocument.entity.User;

import java.time.OffsetDateTime;

public record UserResponseDto(
        Long userId,
        String fullName,
        String email,
        OffsetDateTime createdAt
) {
    public UserResponseDto fromUser(User user) {
        return new UserResponseDto(
                user.getUserId(),
                user.getFullName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
