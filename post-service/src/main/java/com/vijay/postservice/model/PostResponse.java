package com.vijay.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private String id;

    private Long userId;

    private String title;

    private String description;

    private String content;


    private Set<CommentResponse> comments=new HashSet<>();
}
