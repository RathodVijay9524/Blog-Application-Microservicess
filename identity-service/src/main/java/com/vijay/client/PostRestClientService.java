package com.vijay.client;

import com.vijay.model.PostResponses;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
@Service
public class PostRestClientService {

    private final RestClient restClient;

    public PostRestClientService() {
        restClient = RestClient.builder()
                .baseUrl("http://POST-SERVICE")
                .build();
    }

    public PostResponses getPostByUserId(Long userId) {
        PostResponses postResponses = restClient.get()
                .uri("/posts/users/{userId}", userId)
                .retrieve()
                .body(PostResponses.class);
        return postResponses;
    }
}
