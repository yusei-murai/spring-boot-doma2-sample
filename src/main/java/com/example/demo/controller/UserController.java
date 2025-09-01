package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.entity.UserWithEmails;
import com.example.demo.dto.UserWithEmailsDto;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UserOutputDto;
import com.example.demo.dto.EmailOutputDto;
import com.example.demo.service.UserAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserAggregateService userAggregateService;

    @GetMapping
    public List<User> getAllUsers() {
        return userDao.selectAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userDao.selectById(id);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userDao.selectByEmail(email);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/with-emails")
    public List<UserOutputDto> getUsersWithEmails() {
        List<User> users = userDao.selectUserWithEmails();
        return users.stream()
            .map(this::convertToOutputDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}/with-emails")
    public ResponseEntity<UserOutputDto> getUserWithEmailsById(@PathVariable Long id) {
        List<User> results = userDao.selectUserWithEmailsById(id);
        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToOutputDto(results.get(0)));
    }

    @PostMapping("/with-emails")
    public ResponseEntity<User> createUserWithEmails(@RequestBody CreateUserRequest request) {
        User userAggregate = userAggregateService.createUserWithEmails(
            request.name(), 
            request.email(), 
            request.emailAddresses()
        );
        return ResponseEntity.ok(userAggregate);
    }
    
    private UserOutputDto convertToOutputDto(User user) {
        List<EmailOutputDto> emailDtos = user.emails().stream()
            .map(email -> new EmailOutputDto(email.id(), email.mail()))
            .collect(Collectors.toList());
        
        return new UserOutputDto(
            user.id(),
            user.name(),
            user.email(),
            emailDtos
        );
    }

}
