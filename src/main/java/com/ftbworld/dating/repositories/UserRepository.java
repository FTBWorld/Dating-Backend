package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;

public interface UserRepository {

    User registerUser(String username, String password);

    User getUserByUsername(String username);

    User getUserByUsernameAndPassword(String username, String password);

}
