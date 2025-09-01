package com.example.demo.dao;

import com.example.demo.entity.User;
import com.example.demo.entity.Email;
import com.example.demo.entity.UserWithEmails;
import com.example.demo.dto.UserOutputDto;
import com.example.demo.dto.EmailOutputDto;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.Delete;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.AggregateStrategy;
import org.seasar.doma.AssociationLinker;
import org.seasar.doma.jdbc.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

@Dao
@ConfigAutowireable
public interface UserDao {

    @Select
    List<User> selectAll();

    @Select
    Optional<User> selectById(Long id);

    @Select
    Optional<User> selectByEmail(String email);

    @Select(aggregateStrategy = UserAggregateStrategy.class)
    List<User> selectUserWithEmails();

    @Select(aggregateStrategy = UserAggregateStrategy.class)  
    List<User> selectUserWithEmailsById(Long id);
    
    
    @Insert
    Result<User> insertUser(User user);
    
    @Insert
    Result<Email> insertEmail(Email email);

    @AggregateStrategy(root = User.class, tableAlias = "u")
    interface UserAggregateStrategy {
        
        @AssociationLinker(propertyPath = "emails", tableAlias = "e")
        BiFunction<User, Email, User> emails = (user, email) -> {
            List<Email> updatedEmails = new ArrayList<>(user.emails());
            updatedEmails.add(email);
            return user.withEmails(updatedEmails);
        };
    }

}