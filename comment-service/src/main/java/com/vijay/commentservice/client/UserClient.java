package com.vijay.commentservice.client;

import com.vijay.commentservice.model.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;




@HttpExchange
public interface UserClient {

//    @GetExchange("/api/auth/currents")
//    UserDto getCurrentUser(Principal principal);

    @GetExchange("/api/auth/current")
    UserDto curruntUser();
}
