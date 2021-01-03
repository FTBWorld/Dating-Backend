package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.Like;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikeRepositoryImpl implements LikeRepository {

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public Like insertLikeByUserIDs(ObjectId userID, ObjectId likedUserID) {
        Like like = new Like(userID, likedUserID);

        mongoTemplate.save(like);

        return like;
    }

    @Override
    public List<Like> findLikesByUserID(ObjectId userID) {
        return null;
    }

    @Override
    public List<Like> findLikesOfUserID(ObjectId likedUserID) {
        return null;
    }

    @Override
    public Like findLikeByID(ObjectId id) {
        return null;
    }
}
