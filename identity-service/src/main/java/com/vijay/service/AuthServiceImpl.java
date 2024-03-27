package com.vijay.service;

import com.vijay.entites.Role;
import com.vijay.entites.User;
import com.vijay.entites.Worker;
import com.vijay.exceptions.BlogAPIException;
import com.vijay.model.UserDto;
import com.vijay.repository.RoleRepo;
import com.vijay.repository.UserRepository;
import com.vijay.repository.WorkerRepository;
import com.vijay.request.LoginRequest;
import com.vijay.request.RegistraonRequest;
import com.vijay.response.LoginJWTResponse;
import com.vijay.response.RegistraonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.HashSet;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private WorkerRepository workerRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepo roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public void validateToken(String token) {
		jwtTokenProvider.validateToken(token);
	}

	@Override
	public LoginJWTResponse login(LoginRequest req) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(req.getUsernameOrEmail(), req.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsernameOrEmail());

		String token = jwtTokenProvider.generateToken(authentication);

		UserDto response = mapper.map(userDetails, UserDto.class);

		LoginJWTResponse jwtResponse = LoginJWTResponse.builder()
				.jwtToken(token)
				.user(response).build();

		return jwtResponse;
	}

	@Override
	public String register(RegistraonRequest req) {

		// Add check for username exists in database
		if (userRepository.existsByUsername(req.getUsername())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username already exists.");
		}

		// Add check for email exists in database
		if (userRepository.existsByEmail(req.getEmail())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email already exists.");
		}

		// Map the request to a new User object
		User user = mapper.map(req, User.class);

		// Set the password after encoding
		user.setPassword(passwordEncoder.encode(req.getPassword()));

		// Fetch the user role from the repository
		Role userRole = roleRepository.findByName("ROLE_NORMAL").orElseThrow(() -> new BlogAPIException(HttpStatus.INTERNAL_SERVER_ERROR, "Default role not found."));

		// Initialize the roles field if it's null
		if (user.getRoles() == null) {
			user.setRoles(new HashSet<>());
		}

		// Add the user role to the roles set
		user.getRoles().add(userRole);

		// Save the user to the database
		userRepository.save(user);

		return "User registered successfully.";
	}

	@Override
	public RegistraonResponse registerWorker(RegistraonRequest req) {
		// add check for username exists in database
		if (userRepository.existsByUsername(req.getUsername())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
		}

		// add check for email exists in database
		if (userRepository.existsByEmail(req.getEmail())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
		}

		UserDto currentUser = userService.getCurrentUser();
		User user = mapper.map(currentUser, User.class);

		Worker worker = mapper.map(req, Worker.class);
		worker.setPassword(passwordEncoder.encode(req.getPassword()));

		// Fetch the worker role from the repository
		Role workerRole = roleRepository.findByName("ROLE_WORKER").orElseThrow(() -> new BlogAPIException(HttpStatus.INTERNAL_SERVER_ERROR, "Worker role not found."));

		// Initialize the roles field if it's null
		if (worker.getRoles() == null) {
			worker.setRoles(new HashSet<>());
		}

		// Add the worker role to the roles set
		worker.getRoles().add(workerRole);

		worker.setUser(user);
		workerRepository.save(worker);

		return mapper.map(worker, RegistraonResponse.class);
	}

}
