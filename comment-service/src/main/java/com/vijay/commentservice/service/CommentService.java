package com.vijay.commentservice.service;

import com.vijay.commentservice.model.CommentRequest;
import com.vijay.commentservice.model.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<CommentResponse> getAllComments();
    Optional<CommentResponse> getCommentById(String id);
    CommentResponse createComment(CommentRequest comment);
    CommentResponse updateComment(String id, CommentRequest updatedComment);
    void deleteComment(String id);

    List<CommentResponse> findCommentByPostId(String postId);


}
