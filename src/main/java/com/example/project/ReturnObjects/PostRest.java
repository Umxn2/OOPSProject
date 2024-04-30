package com.example.project.ReturnObjects;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class PostRest implements Serializable {
    private int postID;
    private String postBody;
    private LocalDate date;
    private List<CommentRest> comments;

    public int getPostID() {
        return postID;
    }

    public void setPostId(int postId) {
        this.postID = postId;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<CommentRest> getComments() {
        return comments;
    }

    public void setComments(List<CommentRest> comments) {
        this.comments = comments;
    }

    public PostRest(int postId, String postBody, LocalDate date, List<CommentRest> comments) {
        this.postID = postId;
        this.postBody = postBody;
        this.date = date;
        this.comments = comments;
    }
}
