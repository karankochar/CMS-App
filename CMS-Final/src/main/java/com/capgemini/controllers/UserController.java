package com.capgemini.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.User;
import com.capgemini.exceptions.NoSuchUserException;
import com.capgemini.logger.GlobalLogger;
import com.capgemini.service.UserService;

@RestController

@RequestMapping(path = "user")
public class UserController {

	@Autowired
	private UserService service;

	private Logger logger = GlobalLogger.getLogger(UserController.class);

	/**
	 * @param userId
	 * @return
	 * @throws NoSuchUserException
	 */
	// View user by id
	@GetMapping(path = "byId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable("userId") int userId) throws NoSuchUserException {
		String Methodname = "getUserById()";
		logger.info(Methodname + " called");
		ResponseEntity<User> response = null;
		User result = service.findUserById(userId);
		response = new ResponseEntity<User>(result, HttpStatus.FOUND);
		return response;

	}

	/**
	 * @param user
	 * @param userId
	 * @return
	 * @throws NoSuchUserException
	 */
	// modify user by user id
	@PutMapping(path = "byId/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public User modifydata(@RequestBody User user, @PathVariable("userId") int userId) throws NoSuchUserException {
		return service.modifyUser(user, userId);

	}

}