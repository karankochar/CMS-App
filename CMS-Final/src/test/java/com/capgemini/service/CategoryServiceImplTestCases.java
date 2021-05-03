package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.entities.Category;
import com.capgemini.exceptions.NosuchCategoryFoundException;
import com.capgemini.service.CategoryService;

@SpringBootTest
public class CategoryServiceImplTestCases {

	@Autowired
	private CategoryService categoryService;

	@Test
	void testAddCategoryShouldReturnCategoryObject() throws NosuchCategoryFoundException {
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryTitle("testing");
		Category expected = categoryService.addCategory(category);
		Category actual = categoryService.findCategoryById(expected.getCategoryId());
		assertEquals(expected.getCategoryId(), actual.getCategoryId());
		assertEquals(expected.getCategoryTitle(), actual.getCategoryTitle());

	}

	@Test
	void testFindAllCategoryShouldReturnCategoryObject() throws NosuchCategoryFoundException {
		Category category = new Category();
		category.setCategoryTitle("testing");
		Category category2 = new Category();
		category2.setCategoryTitle("testing2");
		categoryService.addCategory(category);
		categoryService.addCategory(category2);
		List<Category> list = categoryService.FindAllCategory();
		assertEquals(category2.getCategoryId(), list.get(list.size() - 1).getCategoryId());
	}

	@Test
	void TestUpdateCategoryShouldReturnCategoryObject() throws NosuchCategoryFoundException {
		Category category = new Category();
		category.setCategoryTitle("test");
		Category add = categoryService.addCategory(category);
		add.setCategoryId(category.getCategoryId());
		add.setCategoryTitle("test2");
		Category update = categoryService.updateCategory(add);
		assertEquals(add.getCategoryId(), update.getCategoryId());
		assertEquals(add.getCategoryTitle(), update.getCategoryTitle());
	}

	@Test
	void testFindCategoryByIdShouldThrowNoSuchCategoryFoundException() {
		assertThrows(NosuchCategoryFoundException.class, () -> {
			categoryService.findCategoryById(-1);
		});
	}

	@Test
	void testDeleteCategoryByIdShouldThrowNoSuchCategoryFoundException() {
		assertThrows(NosuchCategoryFoundException.class, () -> {
			categoryService.removeCategory(-1);
		});
	}

}
