package com.devictor.duedocument.service;

import com.devictor.duedocument.controller.dto.UserRequestDto;
import com.devictor.duedocument.controller.dto.UserSummaryDto;
import com.devictor.duedocument.entity.User;
import com.devictor.duedocument.exception.UserNotFoundException;
import com.devictor.duedocument.repository.UserRespository;
import com.devictor.duedocument.service.dto.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRespository userRespository;
    private final DocumentService documentService;

    public UserService(UserRespository userRespository, DocumentService documentService) {
        this.userRespository = userRespository;
        this.documentService = documentService;
    }

    public UserResponseDto createUser(UserRequestDto dto) {
        User user = dto.toUser();
        User savedUser = userRespository.save(user);
        return UserResponseDto.fromUser(savedUser);
    }

    protected User findUserEntityById(Long id) {
        return userRespository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id {" + id + "} not found"));
    }

    public UserSummaryDto getUserById(Long userId) {
        User user = findUserEntityById(userId);
        return UserSummaryDto.fromUser(user);
    }

    public UserResponseDto getUserByIdWithDetails(Long userId) {
        User user = findUserEntityById(userId);
        return UserResponseDto.fromUser(user);
    }

    @Transactional
    public boolean deleteUser(Long userId) {
        User user = findUserEntityById(userId);
        documentService.deleteDocumentsByUser(user);
        userRespository.delete(user);
        return true;
    }

    public UserResponseDto updateUser(Long userId, UserRequestDto dto) {
        var user = findUserEntityById(userId);
        user.setFullName(dto.fullName());
        user.setEmail(dto.email());
        user.setPasswordHash(dto.password());
        User saved = userRespository.save(user);
        return UserResponseDto.fromUser(saved);
    }
}
