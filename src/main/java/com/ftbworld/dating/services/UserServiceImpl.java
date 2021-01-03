package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
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
    public User registerUser(String username, String password) {
        if (password.length() < 10) {
            throw new DatingBadRequestException("Password must be more than 10 characters.");
        }

        return userRepository.registerUser(username, password);
    }

    @Override
    public User findUserByID(String id) {
        return userRepository.findUserByID(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public User updateUserByID(String id, User user) {
        return userRepository.updateUserByID(id, user);
    }
}
