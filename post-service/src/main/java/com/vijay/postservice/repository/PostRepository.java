package com.vijay.postservice.repository;

import com.vijay.postservice.entity.Post;
import com.vijay.postservice.model.PostResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByUserId(Long userId);
    // You can add custom queries here if needed
}

