package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entities.Hotel;
import com.hotel.hotelservice.HotelService;
import com.hotel.hotelservice.impl.HotelServiceImpl;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelServiceImpl hotelService;
	

	//create
	@PreAuthorize("hasAuthority('Admin')") 
	@PostMapping
	public Hotel createHotel(@RequestBody Hotel hotel) {
		return this.hotelService.createHotel(hotel);
	}
	
	//getall
	@PreAuthorize("hasAuthority('SCOPE_internal')") 
	@GetMapping("/all")
	public List<Hotel> getAllHotels(){
		return this.hotelService.getAll();
	}
	
	//get by id
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")                     
	@GetMapping("/{hotelId}")
	public Hotel getById(@PathVariable("hotelId") String hotelId) {
		return this.hotelService.getHotel(hotelId);
	}
}
