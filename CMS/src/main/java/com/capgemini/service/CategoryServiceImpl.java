package com.capgemini.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Category;
import com.capgemini.exceptions.NoSuchCategoryException;
import com.capgemini.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository repository;

	@Override
	public Category addCategory(Category category) {
		System.out.println(category);
		return repository.save(category);
	}

	@Override
	public Category findCategoryById(int categoryId) throws NoSuchCategoryException {
		try {
			java.util.Optional<Category> category = repository.findById(categoryId);
			if(category.get() != null ) {
				return category.get();
			}
			}  catch (NoSuchElementException e) {
				throw new NoSuchCategoryException("Category with id " + categoryId + " not found.");
			}
			return null;
	}
	
	
}
