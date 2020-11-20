package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
import com.ftbworld.dating.exceptions.DatingDBException;
import com.ftbworld.dating.exceptions.DatingNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_REGISTER_USER =
            "insert into dating_users(user_id, username, password, display_name, bio) values(nextval('dating_users_seq'), ?, ?, ?, ?)";
    private static final String SQL_GET_USER_BY_USERNAME =
            "select user_id, username, password, display_name, bio from dating_users where username = ?";
    private static final String SQL_GET_USER_BY_ID =
            "select user_id, username, password, display_name, bio from dating_users where user_id = ?";

    @Override
    public User registerUser(String username, String password) {
        try {
            // Password hashing is done in the service rather than here.
            // Therefore, password argument is already stored securely.

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_REGISTER_USER, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, username); // Defaults from here. User can change them later.
                preparedStatement.setString(4, "A new user!");
                return preparedStatement;
            }, keyHolder);

            return getUserByID((int) keyHolder.getKeys().get("user_id"));
        } catch (DuplicateKeyException e) {
            throw new DatingBadRequestException("That username is already taken.");
        }
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("user_id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("display_name"),
                rs.getString("bio"));
    });

    @Override
    public User getUserByUsername(String username) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_GET_USER_BY_USERNAME, new Object[]{username}, userRowMapper);
            return user;
        } catch (EmptyResultDataAccessException e) {
            throw new DatingNotFoundException(String.format("User '%s' not found.", username));
        }
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_GET_USER_BY_USERNAME, new Object[]{username}, userRowMapper);
            if (BCrypt.checkpw(password, user.getPassword())) { // Be careful with order of arguments!
                return user;
            } else {
                // Wrong password.
                throw new DatingAuthException("Wrong username or password.");
            }
        } catch (EmptyResultDataAccessException e) {
            // User does not exist. Give generic error message to attacker.
            throw new DatingAuthException("Wrong username or password.");
        }
    }

    @Override
    public User getUserByID(int user_id) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_GET_USER_BY_ID, new Object[]{user_id}, userRowMapper);
            return user;
        } catch (EmptyResultDataAccessException e) {
            throw new DatingNotFoundException(String.format("User (%s) not found.", user_id));
        }
    }
}
