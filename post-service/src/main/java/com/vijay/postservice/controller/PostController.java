package com.vijay.postservice.controller;

import com.vijay.postservice.model.PostRequest;
import com.vijay.postservice.model.PostResponse;
import com.vijay.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

                       // getPostByUserId with comments
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<PostResponse>> getPostByUserId(@PathVariable Long userId) {
        List<PostResponse> postByUserId = postService.findPostByUserId(userId);
        return new ResponseEntity<>(postByUserId, HttpStatus.OK);
    }
                // getPostById with comments
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable String id) {
        Optional<PostResponse> postResponse = postService.getPostById(id);
        return postResponse.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        PostResponse createdPost = postService.createPost(postRequest);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable String id, @RequestBody PostRequest updatedPostRequest) {
        PostResponse updatedPost = postService.updatePost(id, updatedPostRequest);
        if (updatedPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}

