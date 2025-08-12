package com.devictor.duedocument.controller;

import com.devictor.duedocument.controller.dto.DocumentRequestDto;
import com.devictor.duedocument.service.DocumentService;
import com.devictor.duedocument.service.dto.DocumentResponseDto;
import com.devictor.duedocument.service.dto.DocumentSummaryDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final PagedResourcesAssembler<DocumentSummaryDto> pagedResourcesAssembler;

    public DocumentController(DocumentService documentService, PagedResourcesAssembler<DocumentSummaryDto> pagedResourcesAssembler) {
        this.documentService = documentService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
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

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<PagedModel<EntityModel<DocumentSummaryDto>>> listDocumentsByUser(
            @PathVariable("userId") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dueDate").ascending());
        Page<DocumentSummaryDto> documentsPage = documentService.listDocumentsByUser(userId, pageable);
        PagedModel<EntityModel<DocumentSummaryDto>> pagedModel = pagedResourcesAssembler.toModel(documentsPage);
        return ResponseEntity.ok(pagedModel);
    }
}
