package com.capgemini.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.service.SuperAdminService;
import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserRoleException;

@RestController
@RequestMapping(path = "superadmin")
public class SuperAdminController {

	@Autowired
	SuperAdminService service;

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
}