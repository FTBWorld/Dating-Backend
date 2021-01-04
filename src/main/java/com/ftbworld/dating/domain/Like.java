package com.ftbworld.dating.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document(collection = "likes")
public class Like {

    @MongoId
    private final String id;

    private final String userID; // Someone who likes someone
    private final String likedUserID; // The person someone likes

    @CreatedDate
    private final Date createdAt;

    public Like(String id, String userID, String likedUserID, Date createdAt) {
        this.id = id;
        this.userID = userID;
        this.likedUserID = likedUserID;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getLikedUserID() {
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
