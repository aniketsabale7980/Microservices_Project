package com.user.externalservices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.user.entities.Rating;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@GetMapping("/ratings/users/{userId}")
	List<Rating> getRating(@PathVariable String userId);	

	@PostMapping("/ratings")
	Rating createRating(Rating values);
}
