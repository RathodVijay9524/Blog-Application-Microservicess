package com.vijay.response;

import java.util.Set;


import com.vijay.entites.Role;
import com.vijay.entites.User;
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
public class RegistraonResponse {
	
    private Long id;
    
    private String name;
    
    private String username;
    
    private String email;
   
    private String password;

    private Set<Role> roles;
    
    private User user;
}
