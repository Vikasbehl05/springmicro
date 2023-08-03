package com.example.service.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service.entity.Rating;
import com.example.service.repositrory.RatingRepository;
import com.example.service.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingRepo;

	private Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);

	@Override
	public Rating create(Rating rating) {
		logger.info("into the create ratring service method");
		return ratingRepo.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		logger.info("into the getall ratring service method");
		return ratingRepo.findAll();
	}

	@Override
	public List<Rating> getRatingByUser(String userId) {
		logger.info("into the get rating by user ratring service method");
		return ratingRepo.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotel(String hotelId) {
		logger.info("into the get ratring by hotel service method");
		return ratingRepo.findByHotelId(hotelId);
	}

}
