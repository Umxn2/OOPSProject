package com.example.project.ReturnObjects;

public class UserCleanRest
{
    String name;
    int userID;
    String email;



    public UserCleanRest(String name, int userID, String email){
        this.name = name;
        this.userID = userID;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
