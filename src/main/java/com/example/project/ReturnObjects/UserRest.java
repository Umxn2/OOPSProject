package com.example.project.ReturnObjects;

import java.io.Serializable;
import java.util.List;

public class UserRest implements Serializable {
private String name;
private int userID;
private String email;
private List<PostRest> posts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PostRest> getPosts() {
        return posts;
    }

    public void setPosts(List<PostRest> posts) {
        this.posts = posts;
    }

    public UserRest(String name, int userID, String email, List<PostRest> posts) {
    this.name = name;
    this.userID = userID;
    this.email = email;
    this.posts = posts;
}
}
