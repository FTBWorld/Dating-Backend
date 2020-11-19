package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;
import com.ftbworld.dating.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User validate(String username, String password) throws DatingAuthException {
        return null;
    }

    @Override
    public User register(String username, String password) throws DatingAuthException {
        if (password.length() < 10) {
            throw new DatingAuthException("Password must be more than 10 characters.");
        }

        if (userRepository.findByUsername(username) != null) {
            throw new DatingAuthException("That username is already taken!");
        }

        User user = userRepository.create(username, password);
        return user;
    }
}
