package com.rating.controller;

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

import com.rating.entities.Rating;
import com.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;

	//create 
	@PostMapping
	@PreAuthorize("hasAuthority('Admin')") 
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		try {
			Rating createRating = this.ratingService.createRating(rating);
			return ResponseEntity.status(HttpStatus.CREATED).body(createRating);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	//get all
	@GetMapping("/all")
	public ResponseEntity<List<Rating>> getAll(){
		try {
			List<Rating> allRating = this.ratingService.getAllReating();
			if(allRating.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(allRating);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//get by user id
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')") 
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
		try {
			List<Rating> ratingByUserId = this.ratingService.getRatingByUserId(userId);
			if(ratingByUserId.isEmpty()) return ResponseEntity.ok(null);
			return ResponseEntity.ok(ratingByUserId);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//get by hotel id
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
		try {
			List<Rating> ratingByHotelId = this.ratingService.getRatingByHotelId(hotelId);
			if(ratingByHotelId.isEmpty()) return ResponseEntity.notFound().build();
			return ResponseEntity.ok(ratingByHotelId);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
