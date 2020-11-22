package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.Like;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
import com.ftbworld.dating.exceptions.DatingNotFoundException;

import java.util.List;

// The service is responsible for permission handling. Argument 1 should be the user_id of who is performing the action.
public interface LikeService {

    Like createLikeByUsernames(String actor, String username_b);

    boolean deleteLikeByUsernames(String actor, String username_a, String username_b);

    List<Like> getLikesByUsername(String actor);

    List<Like> getLikesOfUsername(String actor);

    List<Like> getMatchesOfUsername(String actor);

}
