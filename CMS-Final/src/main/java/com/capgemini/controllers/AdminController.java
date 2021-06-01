package com.capgemini.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserDetailsExceptions;
import com.capgemini.exceptions.NoSuchUserException;
import com.capgemini.logger.GlobalLogger;
import com.capgemini.service.AdminServiceImpl;

@CrossOrigin
@RestController
//@RequestMapping()

public class AdminController {

	@Autowired
	AdminServiceImpl service;
	static final String log = " called";
	private Logger logger = GlobalLogger.getLogger(AdminController.class);

	/**
	 * @param user
	 * @return
	 * @throws InvalidUserDetailsExceptions
	 */
	// add an admin user
	@PostMapping(path = "addAdmin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<User> createAdmin(@RequestBody User user) throws InvalidUserDetailsExceptions {
		String Methodname = "createAdmin()";
		logger.info(Methodname + log);

		ResponseEntity<User> response = null;
		User result = service.addAdmin(user);
		if (result != null)
			response = new ResponseEntity<User>(result, HttpStatus.CREATED);
		else
			response = new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		return response;
	}

	/**
	 * @param user
	 * @return
	 * @throws InvalidUserDetailsExceptions
	 */
	// add a user
	@PostMapping(path = "addUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<User> createUser(@RequestBody User user) throws InvalidUserDetailsExceptions {
		String Methodname = "createUser()";
		logger.info(Methodname + log);

		ResponseEntity<User> response = null;
		User result = service.addUser(user);
		if (result != null)
			response = new ResponseEntity<User>(result, HttpStatus.CREATED);
		else
			response = new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		return response;
	}

	/**
	 * @return
	 */
	// get all users
	@GetMapping(path = "viewAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUser() {
		String Methodname = "getAllUser()";
		logger.info(Methodname + log);
		return service.findAllUser();
	}

	/**
	 * @param userId
	 * @return
	 * @throws NoSuchUserException
	 */
	// get user by id
	@GetMapping(path = "search/byId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable("userId") int userId) throws NoSuchUserException {

		String methodname = " getUserById()";
		logger.info(methodname + log);

		ResponseEntity<User> response = null;
		User result = service.findUserById(userId);
		response = new ResponseEntity<User>(result, HttpStatus.OK);
		return response;
	}

	/**
	 * @param userId
	 * @param user
	 * @return
	 * @throws NoSuchUserException
	 */
	// modify user by id
	@PutMapping(path = "modify/byId/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public User ModifyUserById(@PathVariable("userId") int userId, @RequestBody User user) throws NoSuchUserException {

		String Methodname = " ModifyUserById()";
		logger.info(Methodname + log);
		return service.modifyUser(user, userId);
	}

	/**
	 * @param userId
	 * @return
	 * @throws NoSuchUserException
	 */
	// delete a user
	@DeleteMapping(path = "delete/byId/{userId}")
	public boolean DeleteUserById(@PathVariable("userId") int userId) throws NoSuchUserException {
		String Methodname = "DeleteUserById()";
		logger.info(Methodname + log);
		return service.removeUser(userId);
	}

}
