package com.vijay.client;

import com.vijay.model.PostResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;
@HttpExchange
public interface PostClient {

    // getPostByUserId with comments
    @GetExchange("/posts/users/{userId}")
    List<PostResponses> getPostByUserId(@PathVariable Long userId);
}
