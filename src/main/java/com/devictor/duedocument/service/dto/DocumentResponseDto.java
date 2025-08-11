package com.devictor.duedocument.service.dto;

import com.devictor.duedocument.entity.Document;
import com.devictor.duedocument.entity.enums.DocumentStatus;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record DocumentResponseDto(
        Long documentId,
        Long userId,
        String title,
        String description,
        LocalDate dueDate,
        DocumentStatus status
) {
    public static DocumentResponseDto fromDocument(Document document) {
        return new DocumentResponseDto(
                document.getDocumentId(),
                document.getUser().getUserId(),
                document.getTitle(),
                document.getDescription(),
                document.getDueDate(),
                document.getStatus()
                );
    }
}
