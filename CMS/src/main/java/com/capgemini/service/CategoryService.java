package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.Category;
import com.capgemini.exceptions.NosuchCategoryFoundException;

public interface CategoryService {
	public Category addCategory(Category category);

	public Category findCategoryById(int categoryid) throws NosuchCategoryFoundException;

	public Category updateCategory(Category category);

	public boolean removeCategory(int categoryid) throws NosuchCategoryFoundException;
	
	public  List<Category>  FindAllCategory();
	

}
