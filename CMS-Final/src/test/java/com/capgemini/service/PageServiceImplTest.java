package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.entities.Category;
import com.capgemini.entities.Page;
import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserDetailsExceptions;
import com.capgemini.exceptions.NoSuchPageException;
import com.capgemini.exceptions.NoSuchUserException;
import com.capgemini.exceptions.NosuchCategoryFoundException;

@SpringBootTest
public class PageServiceImplTest {
	@Autowired
	PageService pageservice;

	@Autowired
	AdminService adminService;

	@Autowired
	CategoryService categoryService;

	@Test
	void testFindPageByContentShouldShowPage() throws NoSuchPageException, NosuchCategoryFoundException,
			NoSuchUserException, InvalidUserDetailsExceptions {

		User user1 = new User();
		user1.setFullName("Gaurav");
		user1.setUserName("gaurav");
		user1.setEmail("gaurav@cms.com");
		user1.setPassword("gaurav");
		user1.setRole("ROLE_USER");
		user1.setStatus(true);

		User user = adminService.addUser(user1);

		Category category1 = new Category();
		category1.setCategoryTitle("Category34");

		Category category = categoryService.addCategory(category1);

		Page page2 = new Page();
		page2.setPageTitle("Page 1G");
		page2.setContent("GB99 is the best show");

		Page expected = pageservice.addPage(user.getUserId(), category.getCategoryId(), page2);
		List<Page> actual = pageservice.findPageByContent(expected.getContent());

		assertEquals(expected.getPageTitle(), actual.get(actual.size() - 1).getPageTitle());

	}

	@Test
	void testViewAllShouldReturnAllPageObjects() throws NosuchCategoryFoundException, NoSuchUserException,
			NoSuchPageException, InvalidUserDetailsExceptions {
		User user1 = new User();
		user1.setFullName("ViewAll2");
		user1.setUserName("Viewall2");
		user1.setEmail("viewall2@cms.com");
		user1.setPassword("viewall2");
		user1.setRole("ROLE_USER");
		user1.setStatus(true);

		User user = adminService.addUser(user1);

		Category category1 = new Category();
		category1.setCategoryTitle("Category3402");

		Category category = categoryService.addCategory(category1);

		Page page2 = new Page();
		page2.setPageTitle("View All Page2");
		page2.setContent("2B99 is the best show for few people");

		Page expected = pageservice.addPage(user.getUserId(), category.getCategoryId(), page2);
		List<Page> actual = pageservice.viewAllPages();
		assertEquals(expected.getPageTitle(), actual.get(actual.size() - 1).getPageTitle());
	}

	@Test
	void testAddPageShouldAddPage() throws NoSuchPageException, InvalidUserDetailsExceptions,
			NosuchCategoryFoundException, NoSuchUserException {
		User user1 = new User();
		user1.setFullName("AddPage");
		user1.setUserName("addpage");
		user1.setEmail("addpage@cms.com");
		user1.setPassword("addpage");
		user1.setRole("ROLE_USER");
		user1.setStatus(true);

		User user = adminService.addUser(user1);

		Category category1 = new Category();
		category1.setCategoryTitle("CategoryAdd");

		Category category = categoryService.addCategory(category1);

		Page page2 = new Page();
		page2.setPageTitle("Add Page");
		page2.setContent("Adding Page");

		Page expected = pageservice.addPage(user.getUserId(), category.getCategoryId(), page2);
		Page actual = pageservice.viewPageById(expected.getPageId());
		assertEquals(expected.getPageId(), actual.getPageId());

	}

	@Test
	void testModifyPageShouldModifyPage()
			throws NosuchCategoryFoundException, NoSuchUserException, InvalidUserDetailsExceptions {
		User user1 = new User();
		user1.setFullName("Anu");
		user1.setUserName("anupanda");
		user1.setEmail("anupanda@cms.com");
		user1.setPassword("anupanda");
		user1.setRole("ROLE_USER");
		user1.setStatus(true);

		User user = adminService.addUser(user1);

		Category category1 = new Category();
		category1.setCategoryTitle("CategoryModify");

		Category category = categoryService.addCategory(category1);

		Page page2 = new Page();
		page2.setPageTitle("Page Modify");
		page2.setContent("Modify Working");

		Page expected = pageservice.addPage(user.getUserId(), category.getCategoryId(), page2);

		expected.setPageTitle("After modifying");
		expected = pageservice.addPage(user.getUserId(), category.getCategoryId(), page2);
		assertNotEquals(expected.getPageTitle(), "before modifying");
	}

	@Test
	void testViewPageByIdShouldReturnPageOfThatId() throws NoSuchPageException, InvalidUserDetailsExceptions,
			NosuchCategoryFoundException, NoSuchUserException {
		User user1 = new User();
		user1.setFullName("PageById");
		user1.setUserName("pagebyid");
		user1.setEmail("pagebyid@cms.com");
		user1.setPassword("pagebyid");
		user1.setRole("ROLE_USER");
		user1.setStatus(true);

		User user = adminService.addUser(user1);

		Category category1 = new Category();
		category1.setCategoryTitle("PageById");

		Category category = categoryService.addCategory(category1);

		Page page2 = new Page();
		page2.setPageTitle("Page By Id");
		page2.setContent("Page By Id Working");

		Page expected = pageservice.addPage(user.getUserId(), category.getCategoryId(), page2);
		Page actual = pageservice.viewPageById(expected.getPageId());

		assertEquals(expected.getPageId(), actual.getPageId());
		assertEquals(expected.getPageTitle(), actual.getPageTitle());

	}

	@Test
	void testViewPageByIdShouldThrowNoSuchPageException() {
		assertThrows(NoSuchPageException.class, () -> {
			pageservice.viewPageById(-1);
		});
	}

	@Test
	void testRemovePageShouldDeletePageOfThatId() throws NoSuchPageException, NosuchCategoryFoundException,
			NoSuchUserException, InvalidUserDetailsExceptions {
		User user1 = new User();
		user1.setFullName("DeletePage2");
		user1.setUserName("deletepage2");
		user1.setEmail("deletepage2@cms.com");
		user1.setPassword("deletepage2");
		user1.setRole("ROLE_USER");
		user1.setStatus(true);

		User user = adminService.addUser(user1);

		Category category1 = new Category();
		category1.setCategoryTitle("CategoryDelete");

		Category category = categoryService.addCategory(category1);

		Page page2 = new Page();
		page2.setPageTitle("Delete Page By Id");
		page2.setContent("Delete Page By Id Working");

		Page expected = pageservice.addPage(user.getUserId(), category.getCategoryId(), page2);
//		Page actual = pageservice.viewPageById(expected.getPageId());

		Boolean actual = pageservice.removePage(expected.getPageId());

		assertEquals(true, actual);

	}

	@Test
	void testRemovePageByIdShouldThrowNoSuchPageException() {
		assertThrows(NoSuchPageException.class, () -> {
			pageservice.removePage(-1);
		});
	}

	@Test
	void testFindPageByCategoryShouldReturnPageOfThatCategory()
			throws NosuchCategoryFoundException, InvalidUserDetailsExceptions, NoSuchUserException {
		User user1 = new User();
		user1.setFullName("FindCategory3");
		user1.setUserName("findcategory3");
		user1.setEmail("findcategory3@cms.com");
		user1.setPassword("findcategory3");
		user1.setRole("ROLE_USER");
		user1.setStatus(true);

		User user = adminService.addUser(user1);

		Category category1 = new Category();
		category1.setCategoryTitle("CategoryFind3");

		Category category = categoryService.addCategory(category1);

		Page page2 = new Page();
		page2.setPageTitle("Find Page By Category3");
		page2.setContent("Finding Page3");

		Page expected = pageservice.addPage(user.getUserId(), category.getCategoryId(), page2);

		List<Page> actual = pageservice.findPageByCategory(expected.getCategory().getCategoryTitle());
		assertEquals(expected.getCategory().getCategoryTitle(),
				actual.get(actual.size() - 1).getCategory().getCategoryTitle());

	}

	@Test
	void testFindPageByUserShouldReturnPageOfThatUser()
			throws NoSuchUserException, InvalidUserDetailsExceptions, NosuchCategoryFoundException {
		User user1 = new User();
		user1.setFullName("FindByUser");
		user1.setUserName("findbyuser");
		user1.setEmail("findbyuser@cms.com");
		user1.setPassword("findbyuser");
		user1.setRole("ROLE_USER");
		user1.setStatus(true);

		User user = adminService.addUser(user1);

		Category category1 = new Category();
		category1.setCategoryTitle("Categoryfindbyuser");

		Category category = categoryService.addCategory(category1);

		Page page2 = new Page();
		page2.setPageTitle("find by user");
		page2.setContent("Finding Page by User");

		Page expected = pageservice.addPage(user.getUserId(), category.getCategoryId(), page2);

		List<Page> actual = pageservice.findPageByUser(expected.getAuthor().getFullName());
		assertEquals(expected.getAuthor().getFullName(), actual.get(actual.size() - 1).getAuthor().getFullName());
	}

	@Test
	void testFindPageByCategoryAndUserShouldReturnPageOfThatCategoryAndUser() throws NoSuchUserException,
			NosuchCategoryFoundException, InvalidUserDetailsExceptions, NoSuchPageException {
		User user1 = new User();
		user1.setFullName("FindByCategory&User");
		user1.setUserName("findbycategoryy&user");
		user1.setEmail("findbycateuserr@cms.com");
		user1.setPassword("findbycateuser");
		user1.setRole("ROLE_USER");
		user1.setStatus(true);

		User user = adminService.addUser(user1);

		Category category1 = new Category();
		category1.setCategoryTitle("Category&User");

		Category category = categoryService.addCategory(category1);

		Page page2 = new Page();
		page2.setPageTitle("find by cate user");
		page2.setContent("Finding Page by Category and User");

		Page expected = pageservice.addPage(user.getUserId(), category.getCategoryId(), page2);

		List<Page> actual = pageservice.findPageByCategoryAndUser(expected.getCategory().getCategoryTitle(),
				(expected.getAuthor().getFullName()));
		assertEquals(expected.getCategory().getCategoryTitle(),
				actual.get(actual.size() - 1).getCategory().getCategoryTitle());
		assertEquals(expected.getAuthor().getFullName(), actual.get(actual.size() - 1).getAuthor().getFullName());
	}

}
