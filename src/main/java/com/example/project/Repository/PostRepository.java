package com.example.project.Repository;

import com.example.project.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByUserID(Integer userID);
    Post findAllBypostID(Integer userID);
}
