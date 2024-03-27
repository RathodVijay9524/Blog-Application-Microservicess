package com.vijay.commentservice.entity;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("post_comments")
public class Comment {

    @Id
    private String id;
    private String postId;
    private String name;
    private String email;
    private String body;

}
