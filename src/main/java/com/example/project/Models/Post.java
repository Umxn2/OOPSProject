package com.example.project.Models;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int postID;
    private int userID;
    private String postBody;
    @OneToMany
    List<Comment> comments;
    @CreationTimestamp
    private LocalDate date;


    public LocalDate getDate() {
        return date;
    }
//    @PrePersist
//    protected void onCreate() {
//        date = LocalDate.now();
//    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post() {
        this.comments = new ArrayList<>();
    }

}
