package com.mannat.ratingservice.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Rating {
	
	@Id
	private String ratingId;
	private String userId;
	private String hotelId;
	private int rating;
	private String feedback;
	
	@Transient
	private Hotel hotel;
}