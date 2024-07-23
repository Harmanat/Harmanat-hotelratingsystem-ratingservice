package com.mannat.ratingservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mannat.ratingservice.entities.Rating;

@Service
public interface RatingService {
	// createRating
	public Rating createRating(Rating rating);

	// getAllRating
	public List<Rating> getAllRating();

	// get all by userid
	public List<Rating> getRatingByUserId(String userId);
	
	// get all by hotel
	public List<Rating> getRatingByHotelId(String hotelId);
	
}
