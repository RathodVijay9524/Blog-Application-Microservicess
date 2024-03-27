package com.vijay.postservice.service;

import com.vijay.postservice.entity.Post;
import com.vijay.postservice.model.PostRequest;
import com.vijay.postservice.model.PostResponse;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<PostResponse> getAllPosts();
    Optional<PostResponse> getPostById(String id);
    PostResponse createPost(PostRequest post);
    PostResponse updatePost(String id, PostRequest updatedPost);
    void deletePost(String id);

    List<PostResponse> findPostByUserId(Long userId);
}
