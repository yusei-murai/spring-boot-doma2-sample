package com.example.demo.dto;

import java.util.List;

public record CreateUserRequest(
    String name,
    String email,
    List<String> emailAddresses
) {}