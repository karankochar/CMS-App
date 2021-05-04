package com.capgemini.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entities.Page;
import com.capgemini.exceptions.NoSuchPageException;
import com.capgemini.exceptions.NoSuchUserException;
import com.capgemini.exceptions.NosuchCategoryFoundException;
import com.capgemini.logger.GlobalLogger;
import com.capgemini.service.PageService;

@RestController
public class PageController {

	@Autowired
	PageService service;

	private Logger logger = GlobalLogger.getLogger(PageController.class);

	/**
	 * @param userid
	 * @param id
	 * @param page
	 * @return
	 * @throws NosuchCategoryFoundException
	 * @throws NoSuchUserException
	 */
	@PostMapping(path = "user/page/addPage/{userid}/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<Page> createPagebycategory(@PathVariable("userid") int userid,
			@PathVariable("categoryId") int id, @RequestBody Page page)
			throws NosuchCategoryFoundException, NoSuchUserException {
		String Methodname = "createPagebycategory()";
		logger.info(Methodname + " called");

		ResponseEntity<Page> response = null;

		Page result = service.addPage(userid, id, page);
		if (result != null)
			response = new ResponseEntity<Page>(result, HttpStatus.CREATED);
		else
			response = new ResponseEntity<Page>(HttpStatus.BAD_REQUEST);
		return response;
	}

	/**
	 * @param pageId
	 * @return
	 * @throws NoSuchPageException
	 */
	@GetMapping(path = "user/page/byId/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page> getByPageId(@PathVariable("pageId") int pageId) throws NoSuchPageException {
		String Methodname = "getByPageId()";
		logger.info(Methodname + " called");
		ResponseEntity<Page> response = null;
		Page result = service.viewPageById(pageId);
		response = new ResponseEntity<Page>(result, HttpStatus.OK);
		return response;

	}

	/**
	 * @param pageId
	 * @return
	 * @throws NoSuchPageException
	 */
	@DeleteMapping(path = "user/page/delete/{pageId}")
	public boolean deletePage(@PathVariable("pageId") int pageId) throws NoSuchPageException {
		String Methodname = "deletePage()";
		logger.info(Methodname + " called");
		return service.removePage(pageId);
	}

	/**
	 * @param userid
	 * @param categoryid
	 * @param page
	 * @return
	 * @throws NosuchCategoryFoundException
	 * @throws NoSuchUserException
	 */
	@PutMapping(path = "user/page/modify/{userId}/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Page UpdatePage(@PathVariable("userId") int userid, @PathVariable("categoryId") int categoryid,
			@RequestBody Page page) throws NosuchCategoryFoundException, NoSuchUserException {
		String Methodname = "editPage()";
		logger.info(Methodname + " called");
		return service.modifyPage(userid, categoryid, page);
	}

	/**
	 * @return
	 */
	@GetMapping(path = "user/page/viewAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Page> viewAllPages() {
		String Methodname = "viewAllPages()";
		logger.info(Methodname + " called");
		return service.viewAllPages();
	}

	/**
	 * @param categoryTitle
	 * @return
	 * @throws NosuchCategoryFoundException
	 */
	@GetMapping(path = "page/byCategory/{categoryTitle}")
	public ResponseEntity<List<Page>> getPageByCategory(@PathVariable("categoryTitle") String categoryTitle)
			throws NosuchCategoryFoundException {
		ResponseEntity<List<Page>> response = null;
		List<Page> result = service.findPageByCategory(categoryTitle);
		response = new ResponseEntity<List<Page>>(result, HttpStatus.FOUND);
		return response;
	}

	/**
	 * @param fullName
	 * @return
	 * @throws NoSuchUserException
	 */
	@GetMapping(path = "admin/page/byUser/{fullName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Page>> getPageByUser(@PathVariable("fullName") String fullName)
			throws NoSuchUserException {
		ResponseEntity<List<Page>> response = null;
		List<Page> result = service.findPageByUser(fullName);
		response = new ResponseEntity<List<Page>>(result, HttpStatus.FOUND);
		return response;
	}

	/**
	 * @param categoryTitle
	 * @param fullName
	 * @return
	 * @throws NosuchCategoryFoundException
	 * @throws NoSuchUserException
	 * @throws NoSuchPageException
	 */
	@GetMapping(path = "admin/page/byCategoryUser/{categoryTitle}/{fullName}", produces = MediaType.APPLICATION_JSON_VALUE) // OK
	public ResponseEntity<List<Page>> getPageByCategoryAndUser(@PathVariable("categoryTitle") String categoryTitle,
			@PathVariable("fullName") String fullName)
			throws NosuchCategoryFoundException, NoSuchUserException, NoSuchPageException {
		ResponseEntity<List<Page>> response = null;
		List<Page> result = service.findPageByCategoryAndUser(categoryTitle, fullName);
		response = new ResponseEntity<List<Page>>(result, HttpStatus.FOUND);
		return response;

	}

	/**
	 * @param userId
	 * @return
	 * @throws NoSuchUserException
	 */
	@GetMapping(path = "admin/page/byUserId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Page>> getPageByUserId(@PathVariable("userId") int userId) throws NoSuchUserException {
		ResponseEntity<List<Page>> response = null;
		List<Page> result = service.findPageByUserId(userId);
		response = new ResponseEntity<List<Page>>(result, HttpStatus.FOUND);
		return response;

	}

	/**
	 * @param content
	 * @return
	 * @throws NoSuchPageException
	 */
	@GetMapping(path = "user/page/byContent/{content}")
	public List<Page> getPageByContent(@PathVariable("content") String content) throws NoSuchPageException {
		return service.findPageByContent(content);
	}

}
