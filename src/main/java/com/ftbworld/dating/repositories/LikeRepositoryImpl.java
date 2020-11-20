package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.Like;
import com.ftbworld.dating.exceptions.DatingDBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
            // TODO: Not sure if I like DB exception for both expected and unexpected behaviour.
            throw new DatingDBException("Like already exists.");
        } catch (Exception e) {
            e.printStackTrace();

            throw new DatingDBException("Could not create like in DB.");
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
        return null;
    }

    @Override
    public List<Like> getLikesOfUser(int user_id) {
        return null;
    }

    @Override
    public List<Like> getMatchesOfUser(int user_id) {
        return null;
    }

    @Override
    public Like getLikeByID(int like_id) {
        List<Like> list = jdbcTemplate.query(SQL_GET_LIKE_BY_ID, new Object[]{like_id}, likeRowMapper);
        if (list.size() > 0) {
            Like like = list.get(0);
            return like;
        } else {
            return null;
        }
    }
}
