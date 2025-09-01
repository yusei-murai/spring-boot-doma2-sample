package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.seasar.doma.jdbc.Result;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserAggregateService {

    @Autowired
    private UserDao userDao;
    
    public User createUserWithEmails(String name, String email, List<String> emailAddresses) {
        User insertedUser = insertUserOnly(name, email);
        
        for (String mailAddress : emailAddresses) {
            Email emailWithUserId = new Email(
                null, 
                insertedUser.id(), 
                mailAddress, 
                LocalDateTime.now(), 
                LocalDateTime.now()
            );
            userDao.insertEmail(emailWithUserId);
        }
        
        return userDao.selectUserWithEmailsById(insertedUser.id()).get(0);
    }
    
    private User insertUserOnly(String name, String email) {
        User userToInsert = new User(
            null, 
            name, 
            email, 
            LocalDateTime.now(), 
            LocalDateTime.now()
        );
        
        Result<User> result = userDao.insertUser(userToInsert);
        if (result.getCount() == 0) {
            throw new RuntimeException("Failed to insert user");
        }
        
        return result.getEntity();
    }
}