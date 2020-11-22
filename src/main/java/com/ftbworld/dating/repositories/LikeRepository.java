package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.Like;

import java.util.List;

// The repository just does database actions. The service is responsible for handling permissions.
public interface LikeRepository {

    Like createLikeByUsernames(String username_a, String username_b);

    void deleteLikeByUsernames(String username_a, String username_b);

    List<Like> getLikesByUsername(String username_a);

    List<Like> getLikesOfUsername(String username_b);

    List<Like> getMatchesOfUsername(String username);

    Like getLikeByUsernames(String username_a, String username_b);

}
