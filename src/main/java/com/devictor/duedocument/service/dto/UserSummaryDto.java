package com.devictor.duedocument.service.dto;

import com.devictor.duedocument.entity.User;

public record UserSummaryDto(
        Long userId,
        String fullName,
        String email
) {

    public static UserSummaryDto fromUser(User user) {
        return new UserSummaryDto(
                user.getUserId(),
                user.getFullName(),
                user.getEmail()
        );
    }
}
