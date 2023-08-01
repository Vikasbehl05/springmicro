package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {

		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		logger.info("String Id genrated is ", randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();

	}

	@Override
	public User deleteUser(String id) {
		Optional<User> tempUser = userRepository.findById(id);
		logger.info("user to be delted is ", tempUser.get().getName());
		userRepository.deleteById(id);
		return tempUser.get();
	}

	@Override
	public User getUser(String id) {
		Optional<User> tempUser = userRepository.findById(id);
		// Fetch rating of the user by id

		return tempUser.get();
	}

}
