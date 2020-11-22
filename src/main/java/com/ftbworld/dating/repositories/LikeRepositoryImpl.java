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

    private static final String SQL_CREATE_LIKE =
            "insert into dating_likes(like_id, user_id, liked_user) values(nextval('dating_likes_seq'), ?, ?)";

    private static final String SQL_GET_LIKE_BY_ID =
            "select like_id, user_id, liked_user from dating_likes where like_id = ?";

    private static final String SQL_GET_LIKES_BY_USER =
            "select like_id, user_id, liked_user from dating_likes where user_id = ?";

    private static final String SQL_GET_LIKES_OF_USER =
            "select like_id, user_id, liked_user from dating_likes where liked_user = ?";

    @Override
    public Like createLike(int user_id, int liked_user) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_LIKE, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, user_id);
                preparedStatement.setInt(2, liked_user);
                return preparedStatement;
            }, keyHolder);

            return getLikeByID((int) keyHolder.getKeys().get("like_id"));
        } catch (DuplicateKeyException e) {
            throw new DatingBadRequestException(String.format("A Like between users (%s) and (%s) already exists.", user_id, liked_user));
        } catch (DataIntegrityViolationException e) {
            throw new DatingBadRequestException(String.format("Can't like user (%s) - not found.", liked_user));
        }
    }

    private RowMapper<Like> likeRowMapper = ((rs, rowNum) -> {
        Like like = new Like(rs.getInt("like_id"),
                rs.getInt("user_id"),
                rs.getInt("liked_user"));
        return like;
    });

    @Override
    public void deleteLike(int like_id) {

    }

    @Override
    public List<Like> getLikesByUser(int user_id) {
        List<Like> likes = jdbcTemplate.query(SQL_GET_LIKES_BY_USER, new Object[]{user_id}, likeRowMapper);
        return likes;
    }

    @Override
    public List<Like> getLikesOfUser(int user_id) {
        List<Like> likes = jdbcTemplate.query(SQL_GET_LIKES_OF_USER, new Object[]{user_id}, likeRowMapper);
        return likes;
    }

    @Override
    public List<Like> getMatchesOfUser(int user_id) {
        return null;
    }

    // TODO: you shouldn't be able to find a like with an ID if it doesn't involve you!
    @Override
    public Like getLikeByID(int like_id) {
        try {
            Like like = jdbcTemplate.queryForObject(SQL_GET_LIKE_BY_ID, new Object[]{like_id}, likeRowMapper);
            return like;
        } catch (EmptyResultDataAccessException e) {
            throw new DatingNotFoundException(String.format("Like (%s) not found.", like_id));
        }
    }
}
