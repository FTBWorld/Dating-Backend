package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.Like;

import java.util.List;

// The repository just does database actions. The service is responsible for handling permissions.
public interface LikeRepository {

    // TODO: renaming in User repository, service, resource...

    // TODO: Think about exceptions here.

    // TODO: Don't let a user like themselves.
    Like createLike(int user_id, int liked_user);

    void deleteLike(int like_id);

    List<Like> getLikesByUser(int user_id);

    List<Like> getLikesOfUser(int user_id);

    List<Like> getMatchesOfUser(int user_id);

    Like getLikeByID(int like_id);

}
