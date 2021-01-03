package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User registerUser(String username, String password) {
        Date date = new Date();
        password = BCrypt.hashpw(password, BCrypt.gensalt(10));
        User user = new User(username, password, username, "A new user...");

        mongoTemplate.save(user);

        return user;
    }

    @Override
    public User findUserByID(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User findUserByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));

        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user = findUserByUsername(username);

        boolean correctPassword = BCrypt.checkpw(password, user.getPassword());
        if (correctPassword) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User updateUserByID(String id, User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        // Only update certain fields
        Date date = new Date();
        mongoTemplate.updateFirst(query, Update.update("username", user.getUsername()), User.class);
        mongoTemplate.updateFirst(query, Update.update("displayName", user.getDisplayName()), User.class);
        mongoTemplate.updateFirst(query, Update.update("bio", user.getBio()), User.class);
        mongoTemplate.updateFirst(query, Update.update("updatedAt", new Date()), User.class);

        // Return the updated copy
        return findUserByID(id);
    }
}
