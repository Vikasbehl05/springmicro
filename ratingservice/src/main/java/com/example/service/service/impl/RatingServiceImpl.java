package com.example.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service.entity.Rating;
import com.example.service.repositrory.RatingRepository;
import com.example.service.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingRepo;

	@Override
	public Rating create(Rating rating) {

		return ratingRepo.save(rating);
	}

	@Override
	public List<Rating> getRatings() {

		return ratingRepo.findAll();
	}

	@Override
	public List<Rating> getRatingByUser(String userId) {

		return ratingRepo.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotel(String hotelId) {

		return ratingRepo.findByHotelId(hotelId);
	}

}
