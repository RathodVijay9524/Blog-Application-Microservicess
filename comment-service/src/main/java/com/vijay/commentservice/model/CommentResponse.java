package com.vijay.commentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private String id;
    private String name;
    private String email;
    private String body;
    private String postId;

}
