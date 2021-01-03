package com.ftbworld.dating.domain;

// TODO: created of a like.
// TODO: the created of a match is the largest of the 2 likes.

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document(collection = "likes")
public class Like {

    @MongoId
    private final ObjectId id;

    private final ObjectId userID; // Someone who likes someone
    private final ObjectId likedUserID; // The person someone likes

    @CreatedDate
    private final Date createdAt;

    public Like(ObjectId id, ObjectId userID, ObjectId likedUserID, Date createdAt) {
        this.id = id;
        this.userID = userID;
        this.likedUserID = likedUserID;
        this.createdAt = createdAt;
    }

    public ObjectId getId() {
        return id;
    }

    public ObjectId getUserID() {
        return userID;
    }

    public ObjectId getLikedUserID() {
        return likedUserID;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
