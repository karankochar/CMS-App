package com.capgemini.controllers;

import java.util.List;

import org.slf4j.Logger;
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
import com.capgemini.logger.GlobalLogger;
import com.capgemini.service.CategoryService;

@RestController
@ControllerAdvice
public class CategoryController {

	@Autowired
	private CategoryService service;

	private Logger logger = GlobalLogger.getLogger(CategoryController.class);

	@PostMapping(path = "admin/category/addCategory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Category AddingCategory(@RequestBody Category category) {
		String Methodname = "AddingCategory()";
		logger.info(Methodname + " called");

		System.out.println(category);
		return service.addCategory(category);

	}

	@PutMapping(path = "admin/category/modifyCategory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Category UpdateCategory(@RequestBody Category category) throws NosuchCategoryFoundException {
		String Methodname = " UpdateCategory()";
		logger.info(Methodname + " called");
		System.out.println(category);
		return service.updateCategory(category);

	}

	@GetMapping(path = "admin/category/search/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Category FindCategoryByID(@PathVariable("categoryId") int categoryId) throws NosuchCategoryFoundException {
		String Methodname = "FindCategoryByID()";
		logger.info(Methodname + " called");

		return service.findCategoryById(categoryId);
	}

	@GetMapping(path = "category/viewAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Category> FindAllCategory() {

		String Methodname = " FindAllCategory() ";
		logger.info(Methodname + " called");
		return service.FindAllCategory();
	}

	@DeleteMapping(path = "admin/category/delete/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteByCategory(@PathVariable("categoryId") int id) throws NosuchCategoryFoundException {

		String Methodname = " deleteByCategory";
		logger.info(Methodname + " called");
		return service.removeCategory(id);
	}

	@ExceptionHandler(NosuchCategoryFoundException.class)
	public ResponseEntity<Object> exception(NosuchCategoryFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

}
