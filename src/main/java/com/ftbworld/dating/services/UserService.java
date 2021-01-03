package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.User;

public interface UserService {

    User registerUser(String username, String password);

    User findUserByID(String id);

    User findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);

    User updateUserByID(String id, User user);

}
