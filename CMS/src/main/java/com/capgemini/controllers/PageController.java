package com.capgemini.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Page;
import com.capgemini.service.PageService;


@RestController
@RequestMapping(path = "page")
public class PageController {
	
	@Autowired
	PageService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
	public ResponseEntity<Page> createPage(@RequestBody Page page) {
		ResponseEntity<Page> response = null;
		
		Page result = service.addPage(page);
		if (result != null)
			response = new ResponseEntity<Page>(result, HttpStatus.CREATED);
		else
			response = new ResponseEntity<Page>(HttpStatus.BAD_REQUEST);
		return response;
	}
	
}
