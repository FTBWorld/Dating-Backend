package com.ftbworld.dating.services;

import com.ftbworld.dating.domain.Like;
import org.bson.types.ObjectId;

import java.util.List;

public interface LikeService {

    Like createLikeByUserIDs(String userID, String likedUserID);

    List<Like> findLikesByUserID(String userID);

    List<Like> findLikesOfUserID(String likedUserID);

    Like findLikeByID(String id);

    boolean deleteLikeByID(String id);

}
