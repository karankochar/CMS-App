package com.capgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Category;
import com.capgemini.exceptions.NosuchCategoryFoundException;
import com.capgemini.service.CategoryService;

@RestController
//@RequestMapping(path = "category")
@ControllerAdvice
public class CategoryController {

	@Autowired
	private CategoryService service;

	@PostMapping(path = "admin/category/addCategory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Category AddingCategory(@RequestBody Category category) {
		System.out.println(category);
		return service.addCategory(category);

	}

	@PutMapping(path = "admin/category/modifyCategory",consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Category UpdateCategory(@RequestBody Category category) {
		System.out.println(category);
		return service.updateCategory(category);

	}

	@GetMapping(path = "admin/category/search/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Category FindCategoryByID(@PathVariable("categoryId") int categoryId) throws NosuchCategoryFoundException {
		return service.findCategoryById(categoryId);
	}

	@GetMapping(path = "category", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Category> FindAllCategory() {
		return service.FindAllCategory();
	}

	@DeleteMapping(path = "admin/delete/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteByCategory(@PathVariable("categoryId") int id) throws NosuchCategoryFoundException {
		return service.removeCategory(id);
	}

	@ExceptionHandler(NosuchCategoryFoundException.class)
	public ResponseEntity<Object> exception(NosuchCategoryFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

}
