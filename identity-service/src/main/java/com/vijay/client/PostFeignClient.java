package com.vijay.client;

import com.vijay.model.PostResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@FeignClient(name = "POST-SERVICE")
public interface PostFeignClient {

    @GetMapping("/posts/users/{userId}")
    List<PostResponses> getPostByUserId(@PathVariable Long userId);
}
