package com.devictor.duedocument.service;

import com.devictor.duedocument.repository.UserRespository;
import com.devictor.duedocument.service.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRespository userRespository;

    public UserService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    public UserResponseDto createUser() {
        return null;
    }
}
