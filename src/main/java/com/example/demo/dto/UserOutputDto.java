package com.example.demo.dto;

import java.util.List;

public record UserOutputDto(
    Long id,
    String name,
    String email,
    List<EmailOutputDto> emails
) {}