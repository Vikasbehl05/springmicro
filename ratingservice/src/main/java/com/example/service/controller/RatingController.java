package com.example.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.service.entity.Rating;
import com.example.service.service.impl.RatingServiceImpl;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingServiceImpl ratingService;

	private Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);

	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating) {
		logger.info("into the create rating contoller method");
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	}

	@GetMapping
	public ResponseEntity<List<Rating>> getRatings() {
		logger.info("into the get all rating contoller mehtod");
		return ResponseEntity.ok(ratingService.getRatings());
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
		logger.info("into getrating by user contoller method");
		return ResponseEntity.ok(ratingService.getRatingByUser(userId));

	}

	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
		logger.info("into getrating for hotels contoller method");
		return ResponseEntity.ok(ratingService.getRatingByHotel(hotelId));

	}
}
