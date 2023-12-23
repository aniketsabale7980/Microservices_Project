package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entities.User;
import com.user.services.UserServices;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
@Component
public class UserController {
	
	@Autowired
	public UserServices userServices;

	//create user
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		try {
			
			User saveUser = this.userServices.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	@GetMapping("/{userId}")
	//@CircuitBreaker(name= "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")  generally we cann't use the @CircuitBreaker and @Retry at same time.
	//@Retry(name = "reatingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId){
		
		User user = this.userServices.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	public ResponseEntity<User> ratingHotelFallback(String userID, Exception ex){
		//log.info("Fallback is execited because service is down : ",ex.getMessage());
		ex.printStackTrace();
		User user = User.builder()
			.email("dummy@gmail.com")
			.name("dummy")
			.about("This is the dummy user")
			.userId("12345")
			.build();
			
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<User>>  getAllusers(){
		try {
			List<User> allUser = this.userServices.getAllUser();
			if(allUser.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(allUser);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
		
		
	}
	
	
	
}
