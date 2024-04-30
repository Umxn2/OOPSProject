package com.example.project.Models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Comments")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int commentID;
    private int postID;
    private String commentBody;
    @ManyToOne(cascade = CascadeType.ALL)
    public CommentCreator commentCreator;
public Comment(String commentBody, CommentCreator commentCreator, int postID) {
    this.commentBody = commentBody;
    this.commentCreator = commentCreator;
    this.postID = postID;
}
    public Comment() {

    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public CommentCreator getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(CommentCreator commentCreator) {
        this.commentCreator = commentCreator;
    }
    public int getPostID() {
    return postID;
    }
}
