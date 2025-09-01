package com.example.demo.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import java.time.LocalDateTime;

@Entity(immutable = true)
public record UserWithEmails(
    @Column(name = "userId")
    Long userId,
    @Column(name = "userName")  
    String userName,
    @Column(name = "userEmail")
    String userEmail,
    @Column(name = "userCreatedAt")
    LocalDateTime userCreatedAt,
    @Column(name = "userUpdatedAt")
    LocalDateTime userUpdatedAt,
    
    @Column(name = "emailId")
    Long emailId,
    @Column(name = "emailMail")
    String emailMail,
    @Column(name = "emailCreatedAt")
    LocalDateTime emailCreatedAt,
    @Column(name = "emailUpdatedAt")
    LocalDateTime emailUpdatedAt
) {}