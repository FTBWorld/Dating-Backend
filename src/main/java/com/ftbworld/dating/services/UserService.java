package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.User;

public interface UserService {

    User login(String username, String password);

    User registerUser(String username, String password);

    User getUserByUsername(String username);

    User updateUser(String id, User user);

}
