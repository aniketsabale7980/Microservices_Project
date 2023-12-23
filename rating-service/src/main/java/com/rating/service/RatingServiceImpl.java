package com.rating.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.entities.Rating;
import com.rating.repo.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating createRating(Rating rating) {
		// TODO Auto-generated method stub
		try {
			String ratingId = UUID.randomUUID().toString();
			rating.setRatingId(ratingId);
			 Rating save = this.ratingRepository.save(rating);
			 return save;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Rating> getAllReating() {
		// TODO Auto-generated method stub
		return this.ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		// TODO Auto-generated method stub
		return this.ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		System.out.println("getRatingByHotelId - "+ hotelId);
		return this.ratingRepository.findByHotelId(hotelId);
	}

	
}
