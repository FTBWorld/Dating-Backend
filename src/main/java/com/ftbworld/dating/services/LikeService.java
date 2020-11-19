package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.Like;
import com.ftbworld.dating.exceptions.DatingBadRequestException;
import com.ftbworld.dating.exceptions.DatingNotFoundException;

import java.util.List;

public interface LikeService {

    // TODO: A user can like someone.
    Like createLike(int user_id, int liked_user) throws DatingBadRequestException;

    // TODO: A user can unlike someone.
    void deleteLike(int user_id, int like_id) throws DatingBadRequestException;

    // TODO: A user can view who they liked.
    List<Like> fetchLikesByUser(int user_id);

    // TODO: A user can view who liked them.
    List<Like> fetchLikesOfUser(int user_id);

    // TODO: If a user likes someone, and that someone likes them, it's a match.
    List<Like> fetchMatchesOfUser(int user_id);

    // TODO: A user can view a like that's related to them.
    Like fetchById(int user_id, int like_id) throws DatingNotFoundException;

}
