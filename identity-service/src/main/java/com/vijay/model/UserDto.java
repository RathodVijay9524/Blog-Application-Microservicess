package com.vijay.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import com.vijay.entites.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	
    private Long id;

    private String name;
    
    private String username;
    
    private String email;
   
    private String password;

    private Set<Role> roles;

    private List<PostResponses> posts=new ArrayList<>();

    //private User user;
}
