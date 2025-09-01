package com.example.demo.mapper;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    
    public User toEntity(CreateUserRequest request) {
        return new User(
            null,
            request.name(),
            request.email(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }
}