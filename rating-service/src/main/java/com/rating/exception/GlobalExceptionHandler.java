package com.rating.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rating.payload.ApiResponse;
import com.rating.payload.ApiResponse.ApiResponseBuilder;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> notFoundException(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponseBuilder apiResponse = ApiResponse.builder().message(message).success(false).status(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(HttpStatus.NOT_FOUND);
	}

}
