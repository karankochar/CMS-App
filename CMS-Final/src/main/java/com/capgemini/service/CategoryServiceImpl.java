package com.capgemini.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.entities.Category;
import com.capgemini.exceptions.NosuchCategoryFoundException;
import com.capgemini.logger.GlobalLogger;
import com.capgemini.repository.CategoryRepository;

@org.springframework.stereotype.Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	private Logger logger = GlobalLogger.getLogger(CategoryServiceImpl.class);

	@Override
	public Category addCategory(Category category) {

		String methodname = " addCategory()";
		logger.info(methodname + " called");
		
		return repository.save(category);
	}

	@Override
	public Category findCategoryById(int categoryid) throws NosuchCategoryFoundException {
		String methodname = "findCategoryById()";
		logger.info(methodname + " called");

		try {
			Optional<Category> category = repository.findById(categoryid);
			if (category.get() != null && category.get().getCategoryId() > 0 )
				return category.get();
		} catch (NoSuchElementException e) {
			throw new NosuchCategoryFoundException("Category id " + categoryid + " is not available");

		}
		return null;
	}

	@Override
	public Category updateCategory(Category category) throws NosuchCategoryFoundException {
		String methodname = " updateCategory()";
		logger.info(methodname + " called");
		
		Category result= null;
		if(category.getCategoryId() > 0 ) 
		result = repository.save(category);
		else {
			throw new NosuchCategoryFoundException("Category id " + category.getCategoryId() + " is not available");	}
		return result;
	}

	@Override
	public boolean removeCategory(int categoryid) throws NosuchCategoryFoundException {
		String methodname = "removeCategory()";
		logger.info(methodname + " called");
		Category category = findCategoryById(categoryid);
		if (category != null && category.getCategoryId() > 0) {
			repository.delete(category);
			return true;
		} else {
			throw new NosuchCategoryFoundException("Category id " + categoryid + " is not available");
		}

	}


	@Override
	public List<Category> FindAllCategory() {
		String methodname = "FindAllCategory() ";
		logger.info(methodname + " called");

		return repository.findAll();
	}

}
