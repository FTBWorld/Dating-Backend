package com.ftbworld.dating.domain;

public class User {

    private final String username;
    private final String password; // TODO: some sort of change password feature? Might take too long.

    private String display_name;
    private String bio;

    public User(String username, String password, String display_name, String bio) {
        this.username = username;
        this.password = password;
        this.display_name = display_name;
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
