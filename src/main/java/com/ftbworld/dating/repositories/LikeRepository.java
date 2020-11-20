package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.Like;

import java.util.List;

// The repository just does database actions. The service is responsible for handling permissions.
public interface LikeRepository {

    // TODO: Think about exceptions here.

    // TODO: Don't let a user like themselves.
    Like create(int user_id, int liked_user);

    void delete(int like_id);

    List<Like> fetchLikesByUser(int user_id);

    List<Like> fetchLikesOfUser(int user_id);

    List<Like> fetchMatchesOfUser(int user_id);

    Like fetchLikeById(int like_id);

}
