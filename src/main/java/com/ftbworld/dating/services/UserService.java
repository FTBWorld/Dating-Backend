package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
import com.ftbworld.dating.exceptions.DatingNotFoundException;

public interface UserService {

    // TODO: is auth exception the right one to throw?
    User login(String username, String password);

    User registerUser(String username, String password);

    // TODO: Anyone can view a profile with an ID.
    User getUserByID(int user_id) throws DatingNotFoundException;

    // TODO: A user can update their own profile.
    User updateUserByID(int user_id, User user) throws DatingBadRequestException;

}
