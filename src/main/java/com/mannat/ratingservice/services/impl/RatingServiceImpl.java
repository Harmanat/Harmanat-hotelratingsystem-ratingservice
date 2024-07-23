package com.mannat.ratingservice.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mannat.ratingservice.entities.Hotel;
import com.mannat.ratingservice.entities.Rating;
import com.mannat.ratingservice.repositories.RatingRepository;
import com.mannat.ratingservice.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingRepository ratingRepository;
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Rating createRating(Rating rating) {
		String randomHotelId = UUID.randomUUID().toString();
		rating.setRatingId(randomHotelId);
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		List<Rating> ratingList =  ratingRepository.findAll();
		ratingList.forEach(rating -> {
			String hotelId = rating.getHotelId();
			Hotel hotel = restTemplate.getForObject("http://localhost:9092/hotels/"+hotelId, Hotel.class);
			rating.setHotel(hotel);
		});
		return ratingList;
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		List<Rating> ratingsList = ratingRepository.findByUserId(userId);
//		ratingsList.forEach(rating -> {
//			String hotelId = rating.getHotelId();
//			Hotel hotel = restTemplate.getForObject("http://localhost:9092/hotels/"+hotelId, Hotel.class);
//			rating.setHotel(hotel);
//		});
		
		return ratingsList;
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		List<Rating> ratingList = ratingRepository.findByHotelId(hotelId);
		
		return ratingList;
	}

}
