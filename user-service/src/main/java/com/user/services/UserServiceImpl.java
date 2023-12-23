package com.user.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.entities.Hotel;
import com.user.entities.Rating;
import com.user.entities.User;
import com.user.exception.ResouceNotFoundException;
import com.user.externalservices.HotelService;
import com.user.externalservices.RatingService;
import com.user.repository.UserRepository;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserServices {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public RestTemplate restTemplate;
	
	@Autowired
	public RatingService ratingService;
	
	@Autowired
	public HotelService hotelService;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return this.userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepository.findAll();
		for (User user : users) {
			ArrayList<Rating> ratings = restTemplate
					.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), ArrayList.class);

			if (ratings != null) {
				user.setRatings(ratings);
			}
		}
		return users;
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResouceNotFoundException("User Id is not valid "+userId));
		
		System.out.println("User - "+user.toString());
		
		//get the ratings which given by user
//normal http call using resttemplate
//		Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
//		System.out.println("rating details - "+ ratingOfUser.toString());
//		log.info("{}",ratingOfUser); 
		System.out.println();
		List<Rating> ratings = ratingService.getRating(user.getUserId());
		//List<Rating> ratings = Arrays.asList(ratingOfUser);
		user.setRatings(ratings);

		List<Rating> ratingList = ratings.stream().map(rating -> {
			System.out.println("problem occures getHotelId - "+rating.getHotelId());
			
//normal http call using resttemplate
//			ResponseEntity<Hotel> forEntity = restTemplate
//					.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
			
//using feign client
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			System.out.println("hotel using feign client - "+hotel.toString());
			rating.setHotel(hotel);
			//log.info("response status code {}", forEntity.getStatusCode());
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingList);
		return user;
	}

}
