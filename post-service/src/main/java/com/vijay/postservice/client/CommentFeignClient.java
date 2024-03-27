package com.vijay.postservice.client;


import com.vijay.postservice.model.CommentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "COMMENT-SERVICE")
public interface CommentFeignClient {

    @GetMapping("/comments/posts/{postId}")
    Set<CommentResponse> getCommentByPostId(@PathVariable String postId);
}
