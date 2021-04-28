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
import com.capgemini.exceptions.InvalidUserRoleException;
import com.capgemini.exceptions.NoSuchUserException;
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

	@PostMapping(path = "addAdmin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<User> createAdmin(@RequestBody User user) throws InvalidUserRoleException {
		ResponseEntity<User> response = null;
		User result = service.addAdmin(user);
		if (result != null)
			response = new ResponseEntity<User>(result, HttpStatus.CREATED);
		else
			response = new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		return response;
	}

	@PostMapping(path = "addUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<User> createUser(@RequestBody User user) throws InvalidUserRoleException {
		ResponseEntity<User> response = null;
		User result = service.addUser(user);
		if (result != null)
			response = new ResponseEntity<User>(result, HttpStatus.CREATED);
		else
			response = new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		return response;
	}

	// get all users
	@GetMapping(path = "viewAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUser() {
		return service.findAllUser();
	}

//get user by id
	@GetMapping(path = "search/byId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable("userId") int userId) throws NoSuchUserException {
		ResponseEntity<User> response = null;
		User result = service.findUserById(userId);
		response = new ResponseEntity<User>(result, HttpStatus.FOUND);
		return response;
	}

	// modify user by id

	@PutMapping(path = "modify/byId/{byUserId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public User modifydata(@RequestBody User user) {
		return service.modifyUser(user);
	}

	// delete a user
	@DeleteMapping(path = "delete/byId/{userId}")
	public boolean deleteStudentById(@PathVariable("userId") int userId) throws NoSuchUserException {
		return service.removeUser(userId);
	}

	/*
	 * @GetMapping(path = "search/byUserName/{userName}", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<User>
	 * getUserById(@PathVariable("userName") String userName) throws
	 * NoSuchUserException { ResponseEntity<User> response = null; User result =
	 * service.viewUser(userName); response = new ResponseEntity<User>(result,
	 * HttpStatus.FOUND); return response; }
	 */

}
