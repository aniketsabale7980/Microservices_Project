package com.hotel.exception;

public class ResouceNotFoundException extends RuntimeException{

	public ResouceNotFoundException() {
		// TODO Auto-generated constructor stub
		super("resopuce not found from server !");
	}
	
	public ResouceNotFoundException(String message){
		super(message);
	}
}
