package com.vijay.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@EntityScan
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private String id;
    private String name;
    private String email;
    private String body;

}
