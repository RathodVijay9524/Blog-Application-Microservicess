package com.vijay.postservice.client;

import com.vijay.postservice.model.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Set;

@Service
public class CommentWebClientService {

    private final WebClient webClient;


    @Autowired
    public CommentWebClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://COMMENT-SERVICE").build();
    }

    public Set<CommentResponse> getCommentByPostId(String postId) {
        CommentResponse commentResponse = webClient.get()
                .uri("/comments/posts/{postId}", postId)
                .retrieve()
                .bodyToMono(CommentResponse.class)
                .block(); // Block until response is available
        return (Set<CommentResponse>) commentResponse;
    }

}
