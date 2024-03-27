package com.vijay.postservice.service;

import com.vijay.postservice.client.CommentClient;
import com.vijay.postservice.client.CommentFeignClient;
import com.vijay.postservice.entity.Post;
import com.vijay.postservice.model.PostRequest;
import com.vijay.postservice.model.PostResponse;
import com.vijay.postservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {



    private final PostRepository postRepository;
    @Autowired
    private CommentClient commentClient;
    @Autowired
    private CommentFeignClient commentFeignClient;

    @Override
    public List<PostResponse> findPostByUserId(Long userId) {
        List<Post> postList=postRepository.findByUserId(userId);
        postList.forEach(post -> {
            post.setComments(commentFeignClient.getCommentByPostId(post.getId()));
        });
        return postList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        posts.forEach(post -> {
            post.setComments(commentClient.getCommentByPostId(post.getId()));
        });

        return posts.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    /*@Override
    public Optional<PostResponse> getPostById(String id) {
        return postRepository.findById(id)
                .map(this::convertToResponse);
    }*/

    @Override
    public Optional<PostResponse> getPostById(String id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setComments(commentClient.getCommentByPostId(post.getId()));
            return Optional.of(convertToResponse(post));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        Post post = convertToPost(postRequest);
        Post savedPost = postRepository.save(post);
        return convertToResponse(savedPost);
    }

    @Override
    public PostResponse updatePost(String id, PostRequest updatedPostRequest) {
        Post existingPost = postRepository.findById(id).orElse(null);
        if (existingPost == null) {
            return null; // Or throw an exception as per your design
        }
        Post updatedPost = convertToPost(updatedPostRequest);
        updatedPost.setId(id); // Ensure ID consistency
        Post savedPost = postRepository.save(updatedPost);
        return convertToResponse(savedPost);
    }

    @Override
    public void deletePost(String id) {
        postRepository.deleteById(id);
    }

    private PostResponse convertToResponse(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        postResponse.setDescription(post.getDescription());
        postResponse.setContent(post.getContent());
        postResponse.setComments(post.getComments());
        postResponse.setUserId(post.getUserId());
        // You can add conversion for comments if needed
        return postResponse;
    }

    private Post convertToPost(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());
        post.setContent(postRequest.getContent());
        post.setUserId(postRequest.getUserId());

        // You can add conversion for comments if needed
        return post;
    }
}

