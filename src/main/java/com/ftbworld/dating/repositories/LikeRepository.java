package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.Like;
import org.bson.types.ObjectId;

import java.util.List;

public interface LikeRepository {

    Like insertLikeByUserIDs(ObjectId userID, ObjectId likedUserID);

    List<Like> findLikesByUserID(ObjectId userID);

    List<Like> findLikesOfUserID(ObjectId likedUserID);

    Like findLikeByID(ObjectId id);

}
