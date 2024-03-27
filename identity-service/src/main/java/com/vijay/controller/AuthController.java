package com.vijay.controller;

import java.security.Principal;

import com.vijay.service.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.vijay.model.UserDto;
import com.vijay.request.LoginRequest;
import com.vijay.request.RegistraonRequest;
import com.vijay.response.LoginJWTResponse;
import com.vijay.response.RegistraonResponse;
import com.vijay.service.AuthService;
import com.vijay.service.UserService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;



    // Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<LoginJWTResponse> login(@RequestBody LoginRequest request) {
        LoginJWTResponse login = authService.login(request);
        return ResponseEntity.ok(login);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody LoginRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsernameOrEmail(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return jwtTokenProvider.generateToken(authenticate);
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }

    // Build Register REST API
    @PostMapping(value = {"/signup"})
    public ResponseEntity<String> register(@RequestBody RegistraonRequest request) {
        String response = authService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/worker")
    public ResponseEntity<RegistraonResponse> registerWorker(@RequestBody RegistraonRequest registerDto) {
        RegistraonResponse registerWorker = authService.registerWorker(registerDto);
        return new ResponseEntity<>(registerWorker, HttpStatus.CREATED);
    }

    @GetMapping("/current")
    public ResponseEntity<UserDto> currentUser() {
        UserDto currentUser = userService.getCurrentUser();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @GetMapping("/currents")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        return new ResponseEntity<>(modelMapper
                .map(userDetailsService.loadUserByUsername(principal.getName()),
                        UserDto.class), HttpStatus.OK);
    }


}
