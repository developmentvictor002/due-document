package com.devictor.duedocument.service.dto;

import com.devictor.duedocument.entity.Document;
import com.devictor.duedocument.entity.enums.DocumentStatus;

import java.time.LocalDate;

public record DocumentSummaryDto(
        Long documentId,
        String title,
        LocalDate dueDate,
        DocumentStatus status
) {

    public static DocumentSummaryDto fromDocument(Document document) {
        return new DocumentSummaryDto(
                document.getDocumentId(),
                document.getTitle(),
                document.getDueDate(),
                document.getStatus()
        );
    }
}
