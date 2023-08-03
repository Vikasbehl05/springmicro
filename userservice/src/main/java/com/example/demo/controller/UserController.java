package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);
		logger.info("Into the Post mapping method for controller");
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}

	// all user get
	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUser = userService.getAllUser();
		logger.info("into the get mapping method of the controller ");
		return ResponseEntity.ok(allUser);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable String userId) {
		logger.info("into the getuser controller function ");
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping
	public ResponseEntity<User> deleteUserbyId(@RequestParam String Id) {
		logger.info("into delete controller funtion for user");
		User temp = userService.deleteUser(Id);
		return ResponseEntity.ok(temp);
	}
}
