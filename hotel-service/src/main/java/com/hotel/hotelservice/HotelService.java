package com.hotel.hotelservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.entities.Hotel;

public interface HotelService  {

	//create hotel
	Hotel createHotel(Hotel hotel);
	
	//get hotel by id
	Hotel getHotel(String id);
	
	//get all hotels
	List<Hotel> getAll();
}
