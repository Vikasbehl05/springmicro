package com.example.demo.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	// GET
	@GetMapping("/ratings")
	public Rating getAllRating();

	// Get method by user
	@GetMapping("/ratings/user/{userId}")
	public Rating[] getUserRating(@PathVariable String userId);

	// post
	@PostMapping("/ratings")
	public Rating createRating(Rating values);

}
