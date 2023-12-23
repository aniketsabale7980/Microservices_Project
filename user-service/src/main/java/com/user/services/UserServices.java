package com.user.services;

import java.util.List;

import com.user.entities.User;

public interface UserServices {
	
	//create user
	User saveUser(User user);
	
	//get all users
	List<User> getAllUser();
	
	//get user by userId
	User getUser(String userId);
	
	//TODO - delete and update
	
}
