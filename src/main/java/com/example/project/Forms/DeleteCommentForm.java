package com.example.project.Forms;

import java.io.Serializable;

public class DeleteCommentForm implements Serializable {
    private String commentID;
    public String getCommentId() {
        return commentID;
    }
    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }
    public DeleteCommentForm(String commentID) {
        this.commentID = commentID;
        // System.out.println(this.postID);
    }
    public DeleteCommentForm() {
        // System.out.println(this.postID);
    }
}
