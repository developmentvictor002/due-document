package com.devictor.duedocument.service;

import com.devictor.duedocument.controller.dto.DocumentRequestDto;
import com.devictor.duedocument.entity.Document;
import com.devictor.duedocument.entity.User;
import com.devictor.duedocument.repository.DocumentRepository;
import com.devictor.duedocument.service.dto.DocumentResponseDto;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserService userService;

    public DocumentService(DocumentRepository documentRepository, UserService userService) {
        this.documentRepository = documentRepository;
        this.userService = userService;
    }

    public DocumentResponseDto createDocument(DocumentRequestDto dto) {
        User user = userService.findUserEntityById(dto.userId());
        Document document = dto.toDocument(user);
        Document savedDocument = documentRepository.save(document);
        return DocumentResponseDto.fromDocument(savedDocument);
    }
}
