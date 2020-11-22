package com.ftbworld.dating.domain;

public class Like {

    private final String username_a;
    private final String username_b;

    public String getUsername_a() {
        return username_a;
    }

    public String getUsername_b() {
        return username_b;
    }

    public Like(String username_a, String username_b) {
        this.username_a = username_a;
        this.username_b = username_b;
    }
}
