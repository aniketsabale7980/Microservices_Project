package com.hotel.hotelservice.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.hotel.entities.Hotel;
import com.hotel.exception.ResouceNotFoundException;
import com.hotel.hotelservice.HotelService;
import com.hotel.repo.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;

	
	@Override
	public Hotel createHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		
		try {
			String hotelId = UUID.randomUUID().toString();
			hotel.setId(hotelId);
			
			Hotel saveHotel = this.hotelRepository.save(hotel);
			return saveHotel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Hotel getHotel(String id) {
		// TODO Auto-generated method stub
		return this.hotelRepository.findById(id).orElseThrow(() -> 
		new ResouceNotFoundException("hotel with given id not found !!"));
	}

	@Override
	public List<Hotel> getAll() {
		// TODO Auto-generated method stub
		return this.hotelRepository.findAll();
	}

}
