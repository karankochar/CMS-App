package com.capgemini.controllers;

import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.entities.Category;
import com.capgemini.entities.Page;
import com.capgemini.exceptions.NoSuchPageException;
import com.capgemini.exceptions.NoSuchUserException;
import com.capgemini.exceptions.NosuchCategoryFoundException;
import com.capgemini.logger.GlobalLogger;
import com.capgemini.service.PageService;
import com.capgemini.service.PageServiceImpl;

@RestController
public class PageController {

	@Autowired
	PageService service;

	private Logger logger = GlobalLogger.getLogger(PageController.class);

	@PostMapping(path = "user/page/addPage/{userid}/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<Page> createPagebycategory(@PathVariable("userid") int userid,
			@PathVariable("categoryId") int id, @RequestBody Page page)
			throws NosuchCategoryFoundException, NoSuchUserException {
		String Methodname = "createPagebycategory()";
		logger.info(Methodname + " called");

		ResponseEntity<Page> response = null;

		Page result = service.AddPage(userid, id, page);
		if (result != null)
			response = new ResponseEntity<Page>(result, HttpStatus.CREATED);
		else
			response = new ResponseEntity<Page>(HttpStatus.BAD_REQUEST);
		return response;
	}

	// http://localhost:9090/cms-app/page - POST
	/*
	 * @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =
	 * "application/json") public ResponseEntity<Page> createPage(@RequestBody Page
	 * page) { String Methodname = "createPage()"; logger.info(Methodname +
	 * " called");
	 * 
	 * ResponseEntity<Page> response = null;
	 * 
	 * Page result = service.addPage(page); if (result != null) response = new
	 * ResponseEntity<Page>(result, HttpStatus.CREATED); else response = new
	 * ResponseEntity<Page>(HttpStatus.BAD_REQUEST); return response; }
	 */
	// http://localhost:9090/cms-app/page/byId/1 - GET
	@GetMapping(path = "user/page/byId/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page> getByPageId(@PathVariable("pageId") int pageId) throws NoSuchPageException {
		String Methodname = "getByPageId()";
		logger.info(Methodname + " called");
		ResponseEntity<Page> response = null;
		Page result = service.viewPageById(pageId);
		response = new ResponseEntity<Page>(result, HttpStatus.OK);
		return response;

	}

	// http://localhost:9090/cms-app/page/delete/{id}
	@DeleteMapping(path = "user/page/delete/{pageId}")
	public boolean deletePage(@PathVariable("pageId") int pageId) throws NoSuchPageException {
		String Methodname = "deletePage()";
		logger.info(Methodname + " called");
		return service.removePage(pageId);
	}

	// http://localhost:9090/cms-app/page - PUT
	@PutMapping(path = "user/modify/{userId}/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Page UpdatePage(@PathVariable("userId") int userid, @PathVariable("categoryId") int categoryid,
			@RequestBody Page page) throws NosuchCategoryFoundException, NoSuchUserException {
		String Methodname = "editPage()";
		logger.info(Methodname + " called");
		return service.modifyPage(userid, categoryid, page);
	}

	// http://localhost:9090/cms-app/page -GET
	@GetMapping(path = "user/page/viewAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Page> viewAllPages() {
		String Methodname = "viewAllPages()";
		logger.info(Methodname + " called");
		return service.viewAllPages();
	}

	// http://localhost:9090/cms-app/page/byCategory/{categoryTitle} -GET
	@GetMapping(path = "page/byCategory/{categoryTitle}")
	public List<Page> getPageByCategory(@PathVariable("categoryTitle") String categoryTitle)
			throws NosuchCategoryFoundException {
		return service.findPageByCategory(categoryTitle);
	}

	// http://localhost:9090/cms-app/page/admin/byUser/{fullName}
	@GetMapping(path = "admin/byUser/{fullName}")
	public List<Page> getPageByUser(@PathVariable("fullName") String fullName) throws NoSuchUserException {
		return service.findPageByUser(fullName);
	}

	@GetMapping(path = "admin/byCategoryUser/{categoryTitle}/{fullName}")
	public List<Page> getPageByCategoryAndUser(@PathVariable("categoryTitle") String categoryTitle,
			@PathVariable("fullName") String fullName) {
		return service.findPageByCategoryAndUser(categoryTitle, fullName);
	}

	@GetMapping(path = "user/byContent/{content}")
	public List<Page> getPageByContent(@PathVariable("content") String content) throws NoSuchPageException {
		return service.findPageByContent(content);
	}

}
