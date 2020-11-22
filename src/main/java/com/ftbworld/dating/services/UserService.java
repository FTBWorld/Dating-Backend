package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
import com.ftbworld.dating.exceptions.DatingNotFoundException;

public interface UserService {

    User login(String username, String password);

    User registerUser(String username, String password);

    User getUserByUsername(String username);

    boolean updateUser(User user);

}
