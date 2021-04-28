package com.capgemini.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.entities.Category;
import com.capgemini.exceptions.NosuchCategoryFoundException;
import com.capgemini.repository.CategoryRepository;

@org.springframework.stereotype.Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Override
	public Category addCategory(Category category) {
		return repository.save(category);
	}

	@Override
	public Category findCategoryById(int categoryid) throws NosuchCategoryFoundException {

		try {
			Optional<Category> category = repository.findById(categoryid);
			if (category.get() != null)
				return category.get();
		} catch (NoSuchElementException e) {
			throw new NosuchCategoryFoundException("Category id " + categoryid + " is not available");

		}
		return null;
	}

	@Override
	public Category updateCategory(Category category) {

		return repository.save(category);
	}

	@Override
	public boolean removeCategory(int categoryid) throws NosuchCategoryFoundException {
		Category category = findCategoryById(categoryid);
		if (category != null) {
			repository.delete(category);
			return true;
		} else {
			throw new NosuchCategoryFoundException("Category id " + categoryid + " is not available");
		}

	}

	@Override
	public List<Category> FindAllCategory() {

		return repository.findAll();
	}

}
