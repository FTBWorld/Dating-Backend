package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;

public interface UserRepository {

    User create(String username, String password) throws DatingAuthException;

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    User findById(int id);

}
