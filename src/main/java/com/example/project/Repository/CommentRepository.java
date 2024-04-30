package com.example.project.Repository;

import com.example.project.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllBypostID(Integer postID);
    Comment findAllByCommentID(Integer commentID);
}
