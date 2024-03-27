package com.vijay.postservice.client;

import com.vijay.postservice.model.CommentResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Set;

@HttpExchange
public interface CommentClient {

    // getCommentByPostId
    @GetExchange("/comments/posts/{postId}")
    public Set<CommentResponse> getCommentByPostId(@PathVariable String postId);
}
