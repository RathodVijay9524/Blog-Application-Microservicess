package com.vijay.commentservice.repository;

import com.vijay.commentservice.entity.Comment;
import com.vijay.commentservice.model.CommentResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<CommentResponse> findByPostId(String postId);
}
