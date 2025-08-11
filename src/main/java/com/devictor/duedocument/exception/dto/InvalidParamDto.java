package com.devictor.duedocument.exception.dto;

public record InvalidParamDto(
        String field,
        String reason
) {
}
