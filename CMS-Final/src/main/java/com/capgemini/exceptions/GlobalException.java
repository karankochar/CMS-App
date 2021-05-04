package com.capgemini.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {

	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { NoSuchUserException.class })
	public String NoSuchUserExceptionHandler(Exception e) {
		String body = e.getMessage();
		return body;
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { InvalidUserRoleException.class })
	public String UserRoleExceptionHandler(Exception e) {
		String body = e.getMessage();
		return body;
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { NoSuchPageException.class })
	public String PageExceptionHandler(Exception e) {
		return e.getMessage();
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { InvalidUserDetailsExceptions.class })
	public String UserDetailsExceptionHandler(Exception e) {
		return e.getMessage();
	}
}
