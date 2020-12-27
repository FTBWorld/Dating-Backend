package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
import com.ftbworld.dating.exceptions.DatingNotFoundException;

public interface UserService {

    User login(String username, String password);

    void registerUser(String username, String password);

    User getUserByUsername(String username);

    void updateUser(String id, User user);

}
