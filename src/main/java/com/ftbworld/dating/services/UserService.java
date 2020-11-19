package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;
import com.ftbworld.dating.exceptions.DatingNotFoundException;

public interface UserService {

    // TODO: is auth exception the right one to throw?
    User login(String username, String password) throws DatingAuthException;

    User register(String username, String password) throws DatingAuthException;

    // TODO: Anyone can view a profile with an ID.
    User fetchById(int user_id) throws DatingNotFoundException;

    // TODO: A user can update their own profile.
    User update(int user_id, User user);

}
