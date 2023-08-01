package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

	// save user
	User saveUser(User user);

	// get all user
	List<User> getAllUser();

	// get user by id
	User getUser(String id);

	// delete user
	User deleteUser(String id);

}
