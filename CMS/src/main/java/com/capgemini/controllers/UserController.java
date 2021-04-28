package com.capgemini.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.User;
import com.capgemini.exceptions.NoSuchUserException;
import com.capgemini.service.UserService;

@RestController

@RequestMapping(path = "user")
public class UserController {

	@Autowired
	private UserService service;
              //     add user
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<User> AddingUser(@RequestBody User user) {
		ResponseEntity<User> response = null;
		User result = service.addUser(user);
		if (result != null)
			response = new ResponseEntity<User>(result, HttpStatus.CREATED);
		else
			response = new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		return response;

	} // view user by user id

	@GetMapping(path = "byId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable("userId") int userId) throws NoSuchUserException {
		ResponseEntity<User> response = null;
		User result = service.findUserById(userId);
		response = new ResponseEntity<User>(result, HttpStatus.FOUND);
		return response;

	} // modify user by user id

	@PutMapping(path = "byId/{byUserId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public User modifydata(@RequestBody User user) {
		return service.modifyUser(user);
		
	}

	// get all users

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUser() {
		return service.findAllUsers();
	}


	@DeleteMapping(path = "byId/{userId}")
	public boolean deleteUserById(@PathVariable("userId") int userId) throws NoSuchUserException {
		return service.removeUser(userId);
	}
	

}