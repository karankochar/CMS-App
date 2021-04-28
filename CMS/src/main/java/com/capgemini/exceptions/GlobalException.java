package com.capgemini.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(NoSuchUserException.class)

	public ResponseEntity<String> handleException(NoSuchUserException e) {
		return new ResponseEntity<String>("User  id  not found", HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
