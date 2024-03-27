package com.vijay.postservice.entity;

import com.vijay.postservice.model.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("user_post")
public class Post {

    @Id
    private String id;

    private Long userId;

    private String title;

    private String description;

    private String content;


    private Set<CommentResponse> comments=new HashSet<>();

}
