package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.User;

public interface UserRepository  {

    User register(String username, String password);

    User getUserByUsername(String username);

    User login(String username, String password);

    User updateUser(String id, User user);

}
