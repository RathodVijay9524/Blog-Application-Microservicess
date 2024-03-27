package com.vijay.postservice.client;

import com.vijay.postservice.model.CommentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CommentRestClientService {

    private final RestClient restClient;

    public CommentRestClientService() {
        restClient = RestClient.builder()
                .baseUrl("http://COMMENT-SERVICE")
                .build();
    }

    public CommentResponse getCommentByPostId(String postId) {
        CommentResponse commentResponse = restClient.get()
                .uri("/comments/posts/{postId}", postId)
                .retrieve()
                .body(CommentResponse.class);
        return commentResponse;
    }
}
