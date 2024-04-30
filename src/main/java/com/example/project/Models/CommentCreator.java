package com.example.project.Models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity

public class CommentCreator implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int userID;
    String name;
    public CommentCreator(int userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public CommentCreator() {

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
