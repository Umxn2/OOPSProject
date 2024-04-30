package com.example.project.Forms;

import java.io.Serializable;

public class DeletePostForm implements Serializable {
    private String postID;
    public String getPostId() {
        return postID;
    }
    public void setPostID(String postID) {
        this.postID = postID;
    }
    public DeletePostForm(String postID) {
        this.postID = postID;
       // System.out.println(this.postID);
    }
    public DeletePostForm() {
       // System.out.println(this.postID);
    }
}
