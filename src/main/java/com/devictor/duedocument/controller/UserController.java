package com.devictor.duedocument.controller;

import com.devictor.duedocument.controller.dto.UserRequestDto;
import com.devictor.duedocument.controller.dto.UserSummaryDto;
import com.devictor.duedocument.service.UserService;
import com.devictor.duedocument.service.dto.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserRequestDto dto) {
        UserResponseDto responseDto = userService.createUser(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.userId())
                .toUri();
        return ResponseEntity.created(location).build();
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
}
