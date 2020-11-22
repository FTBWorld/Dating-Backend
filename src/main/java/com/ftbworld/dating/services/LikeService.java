package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.Like;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
import com.ftbworld.dating.exceptions.DatingNotFoundException;

import java.util.List;

// The service is responsible for permission handling. Argument 1 should be the user_id of who is performing the action.
public interface LikeService {

    Like createLikeByUsernames(String actor, String username_b);

    // TODO: A user can unlike someone.
    boolean deleteLikeByUsernames(String actor, String username_a, String username_b);

    List<Like> getLikesByUsername(String actor);

    List<Like> getLikesOfUsername(String actor);

    // TODO: If a user likes someone, and that someone likes them, it's a match.
    List<Like> getMatchesOfUsername(String actor);

}
