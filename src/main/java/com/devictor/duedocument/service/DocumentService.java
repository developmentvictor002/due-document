package com.devictor.duedocument.service;

import com.devictor.duedocument.controller.dto.DocumentRequestDto;
import com.devictor.duedocument.entity.Document;
import com.devictor.duedocument.entity.User;
import com.devictor.duedocument.exception.UserNotFoundException;
import com.devictor.duedocument.repository.DocumentRepository;
import com.devictor.duedocument.repository.UserRespository;
import com.devictor.duedocument.service.dto.DocumentResponseDto;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRespository userRespository;

    public DocumentService(DocumentRepository documentRepository, UserRespository userRespository) {
        this.documentRepository = documentRepository;
        this.userRespository = userRespository;
    }

    public DocumentResponseDto createDocument(DocumentRequestDto dto) {
        User user = userRespository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + dto.userId()));
        Document document = dto.toDocument(user);
        Document savedDocument = documentRepository.save(document);
        return DocumentResponseDto.fromDocument(savedDocument);
    }

    protected void deleteDocumentsByUser(User user) {
        documentRepository.deleteByUser(user);
    }
}
