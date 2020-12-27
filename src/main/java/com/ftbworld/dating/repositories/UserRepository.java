package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository  {

    void register(User user);

    User getUserByUsername(String username);

    User login(String username, String password);

    void updateUser(String id, User user);

}
