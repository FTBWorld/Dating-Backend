package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
import com.ftbworld.dating.exceptions.DatingNotFoundException;
import com.ftbworld.dating.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User login(String username, String password) {
        User user = userRepository.getUserByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public User registerUser(String username, String password) {
        if (password.length() < 10) {
            throw new DatingBadRequestException("Password must be more than 10 characters.");
        }
        // Store passwords securely in the service, so it works in tests and REST.
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

        User user = userRepository.registerUser(username, hashedPassword);
        return user;
    }

    @Override
    public User getUserByID(int user_id) throws DatingNotFoundException {
        return null;
    }

    @Override
    public User updateUserByID(int user_id, User user) throws DatingBadRequestException {
        return null;
    }
}
