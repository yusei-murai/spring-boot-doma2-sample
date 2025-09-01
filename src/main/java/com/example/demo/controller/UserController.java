package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.entity.UserWithEmails;
import com.example.demo.dto.UserWithEmailsDto;
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
    public List<UserWithEmailsDto> getUsersWithEmails() {
        List<UserWithEmails> results = userDao.selectUserWithEmails();
        return convertToDto(results);
    }

    @GetMapping("/{id}/with-emails")
    public ResponseEntity<UserWithEmailsDto> getUserWithEmailsById(@PathVariable Long id) {
        List<UserWithEmails> results = userDao.selectUserWithEmailsById(id);
        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<UserWithEmailsDto> dtos = convertToDto(results);
        return ResponseEntity.ok(dtos.get(0));
    }

    private List<UserWithEmailsDto> convertToDto(List<UserWithEmails> results) {
        Map<Long, UserWithEmailsDto.Builder> builders = new LinkedHashMap<>();
        
        for (UserWithEmails ue : results) {
            builders.computeIfAbsent(ue.userId(), k -> new UserWithEmailsDto.Builder(k))
                   .addEmail(ue.emailId(), ue.emailMail());
        }
        
        return builders.values().stream()
                      .map(UserWithEmailsDto.Builder::build)
                      .collect(Collectors.toList());
    }
}
