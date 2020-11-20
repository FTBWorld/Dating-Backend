package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.User;
import com.ftbworld.dating.exceptions.DatingAuthException;
import com.ftbworld.dating.exceptions.DatingDBException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    // TODO: how come sometimes we return null, and sometimes we throw an exception?

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_CREATE =
            "insert into dating_users(user_id, username, password, display_name, bio) values(nextval('dating_users_seq'), ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_USERNAME = "select user_id, username, password, display_name, bio from dating_users where username = ?";
    private static final String SQL_FIND_BY_ID = "select user_id, username, password, display_name, bio from dating_users where user_id = ?";

    @Override
    public User create(String username, String password) throws DatingAuthException {
        try {
            // Password hashing is done in the service rather than here.
            // Therefore, password argument is already stored securely.

            // TODO: defaults should be set in the service, NOT here.
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, username);
                preparedStatement.setString(4, "A new user!");
                return preparedStatement;
            }, keyHolder);

            return getByID((int) keyHolder.getKeys().get("user_id"));
        } catch (Exception e) {
            throw new DatingDBException("Could not create account in DB.");
        }
    }

    private RowMapper<User> rowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("user_id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("display_name"),
                rs.getString("bio"));
    });

    @Override
    public User getByUsername(String username) {
        // TODO: stop using this deprecated method.
        List<User> list = jdbcTemplate.query(SQL_FIND_BY_USERNAME, new Object[]{username}, rowMapper);
        if (list.size() > 0) {
            User user = list.get(0);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        List<User> list = jdbcTemplate.query(SQL_FIND_BY_USERNAME, new Object[]{username}, rowMapper);
        if (list.size() > 0) {
            User user = list.get(0);

            if (BCrypt.checkpw(password, user.getPassword())) {
                return user;
            } else {
                throw new DatingAuthException("Wrong username or password.");
            }
        } else {
            return null;
        }
    }

    @Override
    public User getByID(int id) {
        List<User> list = jdbcTemplate.query(SQL_FIND_BY_ID, new Object[]{id}, rowMapper);
        if (list.size() > 0) {
            User user = list.get(0);
            return user;
        } else {
            return null;
        }
    }
}
