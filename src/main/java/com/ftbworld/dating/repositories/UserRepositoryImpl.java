package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User register(String username, String password) {
        Date date = new Date();
        password = BCrypt.hashpw(password, BCrypt.gensalt(10));
        User user = new User(username, password, username, "A new user...", date, date);

        mongoTemplate.save(user);

        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));

        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User login(String username, String password) {
        User user = getUserByUsername(username);

        boolean correctPassword = BCrypt.checkpw(password, user.getPassword());
        if (correctPassword) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User updateUser(String id, User user) {

        return user;
    }
}
