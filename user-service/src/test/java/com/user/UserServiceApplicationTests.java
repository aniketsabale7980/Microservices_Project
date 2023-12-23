package com.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.entities.Rating;
import com.user.externalservices.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	
	@Autowired
	private RatingService ratingService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void createRating() {
		Rating rating = Rating.builder().rating(7).userId("").hotelId("").feedback("This is created by feign client!").build();
		System.out.println("New Rating Created in test - "+rating.getRating());
		 Rating saveRating = ratingService.createRating(rating);
		 
		 System.out.println("saveRating - "+ saveRating);
	}

}
