package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.Like;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
import com.ftbworld.dating.exceptions.DatingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class LikeRepositoryImpl implements LikeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_CREATE_LIKE_BY_USERNAMES =
            "insert into dating_likes(username_a, username_b) values(?, ?)";
    private static final String SQL_GET_LIKE_BY_USERNAMES =
            "select * from dating_likes where username_a = ? and username_b = ?";
    private static final String SQL_GET_LIKES_BY_USERNAME =
            "select * from dating_likes where username_a = ?";
    private static final String SQL_GET_LIKES_OF_USERNAME =
            "select * from dating_likes where username_b = ?";
    // TODO: this query is wrong and does not work. 
    private static final String SQL_GET_MATCHES_OF_USER =
            "...";

    @Override
    public Like createLikeByUsernames(String username_a, String username_b) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_LIKE_BY_USERNAMES, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, username_a);
                preparedStatement.setString(2, username_b);
                return preparedStatement;
            }, keyHolder);

            return getLikeByUsernames(username_a, username_b);
        } catch (DuplicateKeyException e) {
            throw new DatingBadRequestException(String.format("A Like between users '%s' and '%s' already exists.", username_a, username_b));
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new DatingBadRequestException(String.format("Can't like user '%s' - not found.", username_b));
        }
    }

    @Override
    public void deleteLikeByUsernames(String username_a, String username_b) {
        // TODO.
    }

    private RowMapper<Like> likeRowMapper = ((rs, rowNum) -> {
        Like like = new Like(rs.getString("username_a"),
                rs.getString("username_b"));
        return like;
    });

    @Override
    public List<Like> getLikesByUsername(String username_a) {
        List<Like> likes = jdbcTemplate.query(SQL_GET_LIKES_BY_USERNAME, new Object[]{username_a}, likeRowMapper);
        return likes;
    }

    @Override
    public List<Like> getLikesOfUsername(String username_b) {
        List<Like> likes = jdbcTemplate.query(SQL_GET_LIKES_OF_USERNAME, new Object[]{username_b}, likeRowMapper);
        return likes;
    }

    @Override
    public List<Like> getMatchesOfUsername(String username) {
        List<Like> likes = jdbcTemplate.query(SQL_GET_MATCHES_OF_USER, new Object[]{username}, likeRowMapper);
        return likes;
    }

    @Override
    public Like getLikeByUsernames(String username_a, String username_b) {
        try {
            Like like = jdbcTemplate.queryForObject(SQL_GET_LIKE_BY_USERNAMES, new Object[]{username_a, username_b}, likeRowMapper);
            return like;
        } catch (EmptyResultDataAccessException e) {
            throw new DatingNotFoundException(String.format("Like between '%s' and '%s' not found.", username_a, username_b));
        }
    }
}
