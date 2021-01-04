package com.ftbworld.dating.repositories;

import com.ftbworld.dating.domain.Like;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class LikeRepositoryImpl implements LikeRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Like createLikeByUserIDs(String userID, String likedUserID) {
        Date date = new Date();
        Like like = new Like(new ObjectId().toHexString(), userID, likedUserID, date);

        mongoTemplate.save(like);

        return like;
    }

    @Override
    public List<Like> findLikesByUserID(String userID) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userID").is(userID));

        return mongoTemplate.find(query, Like.class);
    }

    @Override
    public List<Like> findLikesOfUserID(String likedUserID) {
        Query query = new Query();
        query.addCriteria(Criteria.where("likedUserID").is(likedUserID));

        return mongoTemplate.find(query, Like.class);
    }

    @Override
    public List<Like> findMatchesOfUserByID(String id) {
        // TODO: write this query

        return null;
    }

    @Override
    public Like findLikeByID(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        return mongoTemplate.findOne(query, Like.class);
    }

    @Override
    public Like findLikeByUserIDs(String userID, String likedUserID) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userID").is(userID).and("likedUserID").is(likedUserID));

        return mongoTemplate.findOne(query, Like.class);
    }

    @Override
    public boolean deleteLikeByID(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        DeleteResult deleteResult = mongoTemplate.remove(query, Like.class);
        return deleteResult.getDeletedCount() > 0;
    }
}
