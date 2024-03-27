package com.vijay.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vijay.client.PostClient;
import com.vijay.client.PostFeignClient;
import com.vijay.client.PostRestClientService;
import com.vijay.entites.User;
import com.vijay.exceptions.BlogAPIException;
import com.vijay.exceptions.ResourceNotFoundException;
import com.vijay.helper.Helper;
import com.vijay.model.PostResponses;
import com.vijay.model.UserDto;
import com.vijay.repository.UserRepository;
import com.vijay.response.PageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private PostClient postClient;
	@Autowired
	private PostFeignClient postFeignClient;
	@Autowired
	private PostRestClientService postRestClientService;

	@Override
	public UserDto getCurrentUser() {
		// Retrieve the current authentication object
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Check if authentication is null or if the user is anonymous
		if (authentication == null || !authentication.isAuthenticated()
				|| authentication instanceof AnonymousAuthenticationToken) {
			throw new IllegalStateException("User is not authenticated.");
		}
		String username = authentication.getName();
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		// Check if userDetails is null (user not found)
		if (userDetails == null) {
			throw new IllegalStateException("User details not found.");
		}
		return mapper.map(userDetails, UserDto.class);
	}

	@Override
	public PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
		// pageNumber default starts from 0
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<User> page = userRepository.findAll(pageable);

		PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);

		return response;
	}

	@Override
	public UserDto findUserById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		List<PostResponses> postByUserId= (List<PostResponses>) postRestClientService.getPostByUserId(user.getId());
		user.setPosts(postByUserId);
		return mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(Long userId, UserDto userDto) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setName(userDto.getName());
		user.setName(userDto.getName());
		user.setUsername(userDto.getUsername());
		// user.setEmail(userDto.getEmail());
		if (!userDto.getPassword().equalsIgnoreCase(user.getPassword()))
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		// save data
		User updatedUser = userRepository.save(user);
		return mapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUserById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		userRepository.delete(user);

	}

	@Override
	public UserDto getUserByEmail(String email) {
		Optional<User> findByEmail = userRepository.findByEmail(email);

		try {
			User user = findByEmail.orElseThrow();
			return mapper.map(user, UserDto.class);
		} catch (NoSuchElementException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "User Email Is NotFound");
		}
	}

	@Override
	public List<UserDto> searchUser(String keywords) {
		List<User> users = userRepository.findByNameContaining(keywords);
		return users.stream()
				.map(user -> mapper.map(user, UserDto.class))
				.collect(Collectors.toList());
	}

}
