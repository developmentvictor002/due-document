package com.devictor.duedocument.controller;

import com.devictor.duedocument.controller.dto.UserRequestDto;
import com.devictor.duedocument.service.DocumentService;
import com.devictor.duedocument.service.dto.DocumentSummaryDto;
import com.devictor.duedocument.service.dto.UserSummaryDto;
import com.devictor.duedocument.service.UserService;
import com.devictor.duedocument.service.dto.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final DocumentService documentService;

    public UserController(UserService userService, DocumentService documentService) {
        this.userService = userService;
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto dto) {
        UserResponseDto responseDto = userService.createUser(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.userId())
                .toUri();
        return ResponseEntity.created(location).body(responseDto);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserSummaryDto> getUserById(@PathVariable("userId") Long userId) {
        UserSummaryDto responseDto = userService.getUserById(userId);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(path = "/{userId}/details")
    public ResponseEntity<UserResponseDto> getUserByIdWithDetails(@PathVariable("userId") Long userId) {
        UserResponseDto responseDto = userService.getUserByIdWithDetails(userId);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("userId") Long userId,
                                                      @RequestBody @Valid UserRequestDto dto) {
        UserResponseDto responseDto = userService.updateUser(userId, dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.userId())
                .toUri();
        return ResponseEntity.ok()
                .location(location)
                .body(responseDto);
    }
}
