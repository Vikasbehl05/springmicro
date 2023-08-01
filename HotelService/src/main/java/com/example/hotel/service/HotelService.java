package com.example.hotel.service;

import java.util.List;

import com.example.hotel.entity.Hotel;

public interface HotelService {

	Hotel create(Hotel hotel);

	// get all
	List<Hotel> getAll();

	// get single
	Hotel get(String id);

}
