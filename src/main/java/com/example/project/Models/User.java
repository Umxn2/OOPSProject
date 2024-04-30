package com.example.project.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table
@Table(name = "Users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer userID;
    private String name;
   // @Column
   @NaturalId
    private String email;
   // @Column

   // @Column
    private String password;
    @OneToMany
    public List<Post> posts;


    public User (String email, String name, String password){
        this.email = email;
        this.name = name;
        this.password = password;
        this.posts = new ArrayList<>();
      //  System.out.println(userID);

    }

    public User() {

    }
    public Integer getUserID() {
        return userID;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setPost(List<Post> post) {
        this.posts = post;
    }
    public List<Post> getPost() {
        return posts;
    }


}

