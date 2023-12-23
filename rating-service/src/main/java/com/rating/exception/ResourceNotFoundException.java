package com.rating.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException() {
		super("resouce not available on server !!");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
