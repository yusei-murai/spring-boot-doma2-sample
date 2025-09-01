package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.entity.UserWithEmails;
import com.example.demo.dto.UserWithEmailsDto;
import com.example.demo.dto.CreateUserRequest;
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
    public List<User> getUsersWithEmails() {
        return userDao.selectUserWithEmails();
    }

    @GetMapping("/{id}/with-emails")
    public ResponseEntity<User> getUserWithEmailsById(@PathVariable Long id) {
        List<User> results = userDao.selectUserWithEmailsById(id);
        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(results.get(0));
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

}
