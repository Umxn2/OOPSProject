package com.example.project.ReturnObjects;

import com.example.project.Models.CommentCreator;

import java.io.Serializable;

public class CommentRest implements Serializable {
    private int commentID;
    private String commentBody;
    public CommentCreator commentCreator;
    public CommentRest(int commentID, String commentBody, CommentCreator commentCreator) {
        this.commentID = commentID;
        this.commentBody = commentBody;
        this.commentCreator = commentCreator;
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
}
