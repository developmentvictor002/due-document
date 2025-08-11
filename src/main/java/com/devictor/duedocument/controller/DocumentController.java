package com.devictor.duedocument.controller;

import com.devictor.duedocument.controller.dto.DocumentRequestDto;
import com.devictor.duedocument.service.DocumentService;
import com.devictor.duedocument.service.dto.DocumentResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<Void> createDocument(@Valid @RequestBody DocumentRequestDto dto) {
        DocumentResponseDto responseDto = documentService.createDocument(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.documentId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
