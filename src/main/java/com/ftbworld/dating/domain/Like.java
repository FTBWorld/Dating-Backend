package com.ftbworld.dating.domain;

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

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", userID=" + userID +
                ", likedUserID=" + likedUserID +
                ", createdAt=" + createdAt +
                '}';
    }
}
