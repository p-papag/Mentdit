package com.example.mentdit.repository;

import com.example.mentdit.model.Comment;
import com.example.mentdit.model.Post;
import com.example.mentdit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
