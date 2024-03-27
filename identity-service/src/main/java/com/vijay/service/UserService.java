package com.vijay.service;

import com.vijay.model.UserDto;
import com.vijay.response.PageableResponse;

import java.util.List;



public interface UserService {

	UserDto getCurrentUser();

	PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);

	UserDto findUserById(Long userId);

	UserDto updateUser(Long userId, UserDto userDto);

	void deleteUserById(Long userId);

	UserDto getUserByEmail(String email);

	List<UserDto> searchUser(String keywords);

}
