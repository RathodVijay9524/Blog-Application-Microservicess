package com.vijay.commentservice.model;


import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	

    private String name;
    
    private String username;
    
    private String email;



    
    //private User user;
}
