package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;

public interface UserRepository {

    User create(String username, String password) throws DatingAuthException;

    User findByUsername(String username);

    User findById(int id);

}
