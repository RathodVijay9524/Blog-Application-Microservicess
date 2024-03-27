package com.vijay.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponses {

    private String id;

    private Long userId;

    private String title;

    private String description;

    private String content;

    private Set<CommentResponse> comments=new HashSet<>();


}
