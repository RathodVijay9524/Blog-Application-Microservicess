package com.vijay.client;

import com.vijay.model.PostResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class PostWebClientService {

    private final WebClient webClient;


    @Autowired
    public PostWebClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://POST-SERVICE").build();
    }

//    public List<PostResponses> getPostByUserId(Long userId) {
//        PostResponses postResponses = webClient.get()
//                .uri("/posts/users/{userId}", userId)
//                .retrieve()
//                .bodyToMono(PostResponses.class)
//                .block(); // Block until response is available
//        return postResponses;
//    }
}
