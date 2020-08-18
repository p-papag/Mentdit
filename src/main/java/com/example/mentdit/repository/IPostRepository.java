package com.example.mentdit.repository;

import com.example.mentdit.model.Post;
import com.example.mentdit.model.Submentdit;
import com.example.mentdit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubmentdit(Submentdit submentdit);
    List<Post> findByUser(User user);
}
