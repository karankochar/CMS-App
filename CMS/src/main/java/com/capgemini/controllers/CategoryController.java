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

import com.capgemini.entities.Category;
import com.capgemini.exceptions.NoSuchCategoryException;
import com.capgemini.service.CategoryService;


@RestController
@RequestMapping(path = "category")
public class CategoryController {
	
	@Autowired
	CategoryService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		ResponseEntity<Category> response = null;
		
		Category result = service.addCategory(category);
		if (result != null)
			response = new ResponseEntity<Category>(result, HttpStatus.CREATED);
		else
			response = new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
		return response;
	}
	
	@GetMapping(path = "byId/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> getStudentById(@PathVariable("categoryId") int categoryId) throws NoSuchCategoryException {
		ResponseEntity<Category> response = null;
		Category result = service.findCategoryById(categoryId);
		response = new ResponseEntity<Category>(result, HttpStatus.OK);
		return response;
	}
}
