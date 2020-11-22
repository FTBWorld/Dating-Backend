package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
import com.ftbworld.dating.exceptions.DatingNotFoundException;

public interface UserService {

    User login(String username, String password);

    User registerUser(String username, String password);

    // TODO: Must be logged in, do this on the REST side.
    User getUserByUsername(String username);

    // TODO: A user can update their own profile.
    boolean updateUserByUsername(User user);

}
