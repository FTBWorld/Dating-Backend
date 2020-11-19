package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;

public interface UserService {

    User validate(String username, String password) throws DatingAuthException;

    User register(String username, String password) throws DatingAuthException;

}
