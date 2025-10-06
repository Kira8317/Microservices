package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	/*
	 * Handles resourceNotFoundException and returns a page with the message.
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> GlobalExcetption(ResourceNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		
	}

}
