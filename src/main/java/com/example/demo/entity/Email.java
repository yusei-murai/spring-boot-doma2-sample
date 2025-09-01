package com.example.demo.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import java.time.LocalDateTime;

@Entity(immutable = true)
@Table(name = "emails")
public record Email(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,
    
    Long userId,
    String mail,
    
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}