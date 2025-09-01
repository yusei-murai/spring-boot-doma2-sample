package com.example.demo.dao;

import com.example.demo.entity.User;
import com.example.demo.entity.UserWithEmails;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.Delete;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface UserDao {

    @Select
    List<User> selectAll();

    @Select
    Optional<User> selectById(Long id);

    @Select
    Optional<User> selectByEmail(String email);

    @Select
    List<UserWithEmails> selectUserWithEmails();

    @Select
    List<UserWithEmails> selectUserWithEmailsById(Long id);

}