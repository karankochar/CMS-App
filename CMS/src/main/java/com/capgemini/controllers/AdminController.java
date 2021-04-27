package com.capgemini.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserRoleException;
import com.capgemini.service.AdminServiceImpl;

@RestController
@RequestMapping(path = "admin")
public class AdminController {

	@Autowired
	AdminServiceImpl service;

	@GetMapping()
	public String Admin() {
		return ("<h1>Welcome Admin<h1>");
	}

	@PostMapping(path = "addAdmin" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<User> createAdmin(@RequestBody User user) throws InvalidUserRoleException {
		ResponseEntity<User> response = null;
			User result = service.addAdmin(user);
			if (result != null)
				response = new ResponseEntity<User>(result, HttpStatus.CREATED);
			else
				response = new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
	
	
	@PostMapping(path = "addUser" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<User> createUser(@RequestBody User user) throws InvalidUserRoleException {
		ResponseEntity<User> response = null;
			User result = service.addUser(user);
			if (result != null)
				response = new ResponseEntity<User>(result, HttpStatus.CREATED);
			else
				response = new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		
	
	
	/*
	 * @GetMapping(path = "viewUser/{userId}", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<User>
	 * readUser(@PathVariable("userId") int userId){ ResponseEntity<User> response =
	 * null;
	 * 
	 * }
	 */

}
