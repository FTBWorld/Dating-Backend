package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
import com.ftbworld.dating.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User login(String username, String password) {
        return userRepository.login(username, password);
    }

    @Override
    public User registerUser(String username, String password) {
        if (password.length() < 10) {
            throw new DatingBadRequestException("Password must be more than 10 characters.");
        }

        User user = userRepository.register(username, password);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        return user;
    }

    @Override
    public User updateUser(String id, User user) {
        return userRepository.updateUser(id, user);
    }
}
