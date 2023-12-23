package com.rating.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rating.entities.Rating;

@Component
public interface RatingService {

	//create rating
	Rating createRating(Rating rating);
	
	//getAllRating
	List<Rating> getAllReating();
	
	//get by userId
	List<Rating> getRatingByUserId(String userId);
	
	//get by hotel id
	List<Rating> getRatingByHotelId(String hotelId);
	
}
