package com.example.service.service;

import java.util.List;

import com.example.service.entity.Rating;

public interface RatingService {

	// create
	Rating create(Rating rating);

	// get all ratings
	List<Rating> getRatings();

	// get rating by id
	List<Rating> getRatingByUser(String userId);

	// get rating by hotel
	List<Rating> getRatingByHotel(String hotelId);
}
