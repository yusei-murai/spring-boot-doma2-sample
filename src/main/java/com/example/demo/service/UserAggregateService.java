package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.entity.Email;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.mapper.UserMapper;
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
    
    @Autowired
    private UserMapper userMapper;
    
    public User createUserWithEmails(CreateUserRequest request) {
        User insertedUser = insertUserOnly(userMapper.toEntity(request));
        
        for (String mailAddress : request.emailAddresses()) {
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
    
    private User insertUserOnly(User user) {
        Result<User> result = userDao.insertUser(user);
        if (result.getCount() == 0) {
            throw new RuntimeException("Failed to insert user");
        }
        
        return result.getEntity();
    }
}