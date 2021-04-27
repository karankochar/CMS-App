package com.capgemini.service;

import com.capgemini.entities.Category;
import com.capgemini.exceptions.NoSuchCategoryException;

public interface CategoryService {
	public Category addCategory(Category category);
	public Category findCategoryById(int categoryId) throws NoSuchCategoryException;
}
