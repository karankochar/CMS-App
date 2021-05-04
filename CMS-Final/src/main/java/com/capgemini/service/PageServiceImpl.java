package com.capgemini.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Category;
import com.capgemini.entities.Page;
import com.capgemini.entities.User;
import com.capgemini.exceptions.NoSuchPageException;
import com.capgemini.exceptions.NoSuchUserException;
import com.capgemini.exceptions.NosuchCategoryFoundException;
import com.capgemini.logger.GlobalLogger;
import com.capgemini.repository.CategoryRepository;
import com.capgemini.repository.PageRepository;
import com.capgemini.repository.UserRepository;

@Service
public class PageServiceImpl implements PageService {

	@Autowired
	PageRepository repository;

	@Autowired
	CategoryRepository categoryrepository;

	@Autowired
	UserRepository repository1;

	private Logger logger = GlobalLogger.getLogger(PageServiceImpl.class);

	//get a page from database
	@Override
	public Page viewPageById(int pageId) throws NoSuchPageException {
		String methodname = " viewPageById()";
		logger.info(methodname + " called");
		try {
			Optional<Page> page = repository.findById(pageId);
			if (page.get() != null) {
				return page.get();
			}

		} catch (NoSuchElementException e) {
			logger.warn(methodname + "warning has called");
			throw new NoSuchPageException("Page with id " + pageId + " not found");
		}
		return null;

	}

	//get all pages from database
	@Override
	public List<Page> viewAllPages() {
		String methodname = " viewAllPages()";
		logger.info(methodname + " called");
		return repository.findAll();
	}

	//save updated page to the database
	@Override
	public Page modifyPage(int userid, int categoryid, Page page)
			throws NosuchCategoryFoundException, NoSuchUserException {
		String methodname = " modifyPage()";
		logger.info(methodname + " called");
		Set<Page> page1 = new HashSet<>();
		Category category = new Category();

		Optional<User> user = repository1.findById(userid);
		if (!user.isPresent()) {
			throw new NoSuchUserException("User id " + userid + " is not available ");
		}

		Optional<Category> byId = categoryrepository.findById(categoryid);
		if (!byId.isPresent()) {
			throw new NosuchCategoryFoundException("Category Id " + categoryid + " is not present in database");
		}
		Category category2 = byId.get();
		User user2 = user.get();

		// tie category to page
		page.setCategory(category2);
		page.setAuthor(user2);

		Page page2 = repository.save(page);

		// tie page to category
		page1.add(page2);
		category.setPageList(page1);
		return page2;
	}

	//delete a page from database
	@Override
	public boolean removePage(int pageId) throws NoSuchPageException {
		String methodname = " removePage()";

		boolean result = false;
		Page page = viewPageById(pageId);
		if (page != null && page.getPageId() > 0) {
			// repository.delete(page);
			repository.deletePageById(pageId);
			logger.info(methodname + " called");
			result = true;
			return result;
		} else {
			logger.warn(methodname + "warning has called");
			throw new NoSuchPageException("Page Id " + pageId + " doesn't exist");
		}
	}

	//saving a page to database
	public Page addPage(int userid, int id, Page page) throws NosuchCategoryFoundException, NoSuchUserException {
		Set<Page> page1 = new HashSet<>();
		Category category = new Category();
		Optional<User> user = repository1.findById(userid);
		if (!user.isPresent()) {
			throw new NoSuchUserException("User id " + userid + " is not available ");
		}

		Optional<Category> byId = categoryrepository.findById(id);
		if (!byId.isPresent()) {
			throw new NosuchCategoryFoundException("Category Id " + id + " is not present in database");
		}
		Category category2 = byId.get();
		User user2 = user.get();

		// tie category to page
		page.setCategory(category2);
		page.setAuthor(user2);

		Page page2 = repository.save(page);

		// tie page to category
		page1.add(page2);
		category.setPageList(page1);
		return page2;

	}
	
	//get page by category title from database
	@Override
	public List<Page> findPageByCategory(String categoryTitle) throws NosuchCategoryFoundException {
		List<Page> result = repository.findByPageCategoryTitle(categoryTitle);
		if (!result.isEmpty()) {
			return result;
		} else {
			throw new NosuchCategoryFoundException("No Page with Category " + categoryTitle + " Found");
		}
	}

	//get page by user's full name from database
	@Override
	public List<Page> findPageByUser(String fullName) throws NoSuchUserException {
		List<Page> result = repository.findByPageUser(fullName);
		if (!result.isEmpty()) {
			return result;
		} else {
			throw new NoSuchUserException("No User Found with name " + fullName);
		}
	}

	//get page by category title and user's name
	@Override
	public List<Page> findPageByCategoryAndUser(String categoryTitle, String fullName) throws NoSuchPageException {
		List<Page> result = repository.findByCategoryAndUser(categoryTitle, fullName);
		if (!result.isEmpty()) {
			return result;
		} else {
			throw new NoSuchPageException("No Such Page Exist");
		}
	}

	//get pages by user id
	@Override
	public List<Page> findPageByUserId(int userId) throws NoSuchUserException {
		List<Page> result = repository.findbyUserId(userId);
		if (!result.isEmpty()) {
			return result;
		} else {
			throw new NoSuchUserException("No User Found with id " + userId);
		}

	}

	//get pages by page content
	@Override
	public List<Page> findPageByContent(String content) throws NoSuchPageException {

		List<Page> result = repository.findByContent(content);
		if (!result.isEmpty()) {
			return repository.findByContent(content);

		} else {
			throw new NoSuchPageException("No page with content " + content + " found");
		}
	}

}
