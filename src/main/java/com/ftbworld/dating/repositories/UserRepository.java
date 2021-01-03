package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.User;

public interface UserRepository  {

    User registerUser(String username, String password);

    User findUserByID(String id);

    User findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);

    User updateUserByID(String id, User user);

}
