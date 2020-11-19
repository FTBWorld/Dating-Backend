package com.ftbworld.dating.domain;

public class Like {

    private final int like_id;

    private final int user_id;
    private final int liked_user;

    public Like(int like_id, int user_id, int liked_user) {
        this.like_id = like_id;
        this.user_id = user_id;
        this.liked_user = liked_user;
    }

    public int getLike_id() {
        return like_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getLiked_user() {
        return liked_user;
    }
}
