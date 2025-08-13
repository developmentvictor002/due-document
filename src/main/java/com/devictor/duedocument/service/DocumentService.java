package com.devictor.duedocument.service;

import com.devictor.duedocument.controller.dto.DocumentRequestDto;
import com.devictor.duedocument.entity.Document;
import com.devictor.duedocument.entity.User;
import com.devictor.duedocument.entity.enums.DocumentStatus;
import com.devictor.duedocument.exception.CannotCancelExpiredDocumentException;
import com.devictor.duedocument.exception.DocumentNotFoundException;
import com.devictor.duedocument.exception.InvalidDueDateException;
import com.devictor.duedocument.exception.UserNotFoundException;
import com.devictor.duedocument.repository.DocumentRepository;
import com.devictor.duedocument.repository.UserRespository;
import com.devictor.duedocument.service.dto.DocumentResponseDto;
import com.devictor.duedocument.service.dto.DocumentSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRespository userRespository;

    public DocumentService(DocumentRepository documentRepository, UserRespository userRespository) {
        this.documentRepository = documentRepository;
        this.userRespository = userRespository;
    }

    public DocumentResponseDto createDocument(DocumentRequestDto dto) {
        User user = findUserEntityById(dto.userId());
        if(dto.dueDate().isBefore(LocalDate.now())) {
            throw new InvalidDueDateException("The due date cannot be earlier than the current date");
        }
        Document document = dto.toDocument(user);
        Document savedDocument = documentRepository.save(document);
        return DocumentResponseDto.fromDocument(savedDocument);
    }

    protected void deleteDocumentsByUser(User user) {
        documentRepository.deleteByUser(user);
    }

    public Page<DocumentSummaryDto> listDocumentsByUser(Long userId, Pageable pageable) {
        User user = findUserEntityById(userId);
        return documentRepository.findByUser(user, pageable)
                .map(DocumentSummaryDto::fromDocument);
    }

    private User findUserEntityById(Long id) {
        return userRespository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id {" + id + "} not found"));
    }

    public void markAllAsExpired(List<Document> documents) {
        documents.forEach(doc -> doc.setStatus(DocumentStatus.EXPIRED));
        documentRepository.saveAll(documents);
    }

    public void markAsArchived(Document document) {
        document.setStatus(DocumentStatus.ARCHIVED);
        documentRepository.save(document);
    }

    public void cancelDocument(Long documentId) {
        Document document = findDocumentEntityById(documentId);
        if(document.getStatus() == DocumentStatus.EXPIRED) {
            throw new CannotCancelExpiredDocumentException(STR."Document id with \{document.getDocumentId()} has already expired and cannot be cancelled");
        }
        documentRepository.save(document);
    }

    private Document findDocumentEntityById(Long documentId) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new DocumentNotFoundException(STR."Document with id \{documentId} not found"));
        return document;
    }
}
