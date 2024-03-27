package com.vijay.response;



import com.vijay.model.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginJWTResponse {
	
	 private String jwtToken;
	 private UserDto user;

}
