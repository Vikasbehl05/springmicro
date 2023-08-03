package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.external.services.HotelService;
import com.example.demo.external.services.RatingService;
import com.example.demo.model.Hotel;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	HotelService hotelService;

	@Autowired
	RatingService ratingService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		logger.info("into the create user service method");
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		logger.info("String Id genrated is ", randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		logger.info("into the get all user service method");
		return userRepository.findAll();

	}

	@Override
	public User deleteUser(String id) {
		logger.info("into the delete user service method");
		Optional<User> tempUser = userRepository.findById(id);
		logger.info("user to be delted is ", tempUser.get().getName());
		userRepository.deleteById(id);
		return tempUser.get();
	}

	@Override
	public User getUser(String id) {
		logger.info("into Get single user service");
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("unable to find user by id "));
		// Fetch rating of the user by id
		/*
		 * Rating[] ratingsOfUser =
		 * restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" +
		 * user.getUserId(), Rating[].class);
		 */
		Rating[] ratingsOfUser = ratingService.getUserRating(user.getUserId());
		logger.info("{} ", ratingsOfUser);
		List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());
		List<Rating> ratingList = ratings.stream().map(rating -> {
			// Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" +
			// rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		user.setRating(ratingList);
		return user;
	}

}
