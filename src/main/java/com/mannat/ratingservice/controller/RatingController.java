package com.mannat.ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mannat.ratingservice.entities.Rating;
import com.mannat.ratingservice.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		 Rating createdRating = ratingService.createRating(rating);
		 return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
	}
	
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRating(){
		return ResponseEntity.status(HttpStatus.FOUND).body(ratingService.getAllRating());
	}

	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('ADMIN')")
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
		return ResponseEntity.status(HttpStatus.FOUND).body(ratingService.getRatingByUserId(userId));
	}

	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
		return ResponseEntity.status(HttpStatus.FOUND).body(ratingService.getRatingByHotelId(hotelId));
	}
}
