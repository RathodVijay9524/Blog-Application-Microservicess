package com.vijay.service;


import com.vijay.request.LoginRequest;
import com.vijay.request.RegistraonRequest;
import com.vijay.response.LoginJWTResponse;
import com.vijay.response.RegistraonResponse;

public interface AuthService {
	LoginJWTResponse login(LoginRequest req);

    public void validateToken(String token);

    String register(RegistraonRequest req);
    
    RegistraonResponse registerWorker(RegistraonRequest req);
}
