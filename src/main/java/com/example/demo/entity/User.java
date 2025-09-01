package com.example.demo.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Association;

import java.time.LocalDateTime;
import java.util.List;

@Entity(immutable = true)
@Table(name = "users")
public record User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,
    
    String name,
    String email,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    
    @Association
    List<Email> emails
) {
    public User(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, name, email, createdAt, updatedAt, List.of());
    }
    
    public User withEmails(List<Email> emails) {
        return new User(id, name, email, createdAt, updatedAt, emails);
    }
}
