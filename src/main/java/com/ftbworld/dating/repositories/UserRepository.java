package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository  {

    User registerUser(String username, String password);

    User getUserByUsername(@Param("username") String username);

    User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    boolean updateUser(User user);

}
