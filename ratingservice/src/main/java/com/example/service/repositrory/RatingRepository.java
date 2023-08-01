package com.example.service.repositrory;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.service.entity.Rating;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

	List<Rating> findByUserId(String userId);

	List<Rating> findByHotelId(String HotelId);

}
