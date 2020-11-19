package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;
import com.ftbworld.dating.exceptions.DatingRequestException;
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
    public User login(String username, String password) throws DatingAuthException {
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user == null) {
            // Always give a generic error message, so an attacker doesn't know what was wrong about the credentials.
            throw new DatingAuthException("Wrong username or password.");
        }

        return user;
    }

    @Override
    public User register(String username, String password) throws DatingAuthException {
        if (password.length() < 10) {
            throw new DatingRequestException("Password must be more than 10 characters.");
        }

        if (userRepository.findByUsername(username) != null) {
            throw new DatingRequestException("That username is already taken!");
        }

        User user = userRepository.create(username, password);
        return user;
    }
}
