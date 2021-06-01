package com.capgemini.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.User;
import com.capgemini.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	LoginService service;
	
	@CrossOrigin
	@PostMapping(path = "login", consumes = MediaType.APPLICATION_JSON_VALUE , produces = "application/json")
	public User login(@RequestBody User user) {
		
		return service.login(user);
}

}
