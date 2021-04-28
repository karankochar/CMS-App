package com.capgemini.controllers;

import java.util.List;

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
import com.capgemini.service.PageService;


@RestController
@RequestMapping(path = "page")
public class PageController {

	@Autowired
	PageService service;

	// http://localhost:9090/cms-app/page - POST
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<Page> createPage(@RequestBody Page page) {
		ResponseEntity<Page> response = null;

		Page result = service.addPage(page);
		if (result != null)
			response = new ResponseEntity<Page>(result, HttpStatus.CREATED);
		else
			response = new ResponseEntity<Page>(HttpStatus.BAD_REQUEST);
		return response;
	}

	// http://localhost:9090/cms-app/page/byId/1 - GET
	@GetMapping(path = "byId/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page> getByPageId(@PathVariable("pageId") int pageId) throws NoSuchPageException {
		ResponseEntity<Page> response = null;
		Page result = service.viewPageById(pageId);
		response = new ResponseEntity<Page>(result, HttpStatus.OK);
		return response;

	}
	
	// http://localhost:9090/cms-app/page/{id}
	@DeleteMapping(path = "{pageId}")
	public boolean deletePage(@PathVariable("pageId") int pageId) throws NoSuchPageException {
		return service.removePage(pageId);
	}

	// http://localhost:9090/cms-app/page - PUT
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public Page editPage(@RequestBody Page page) {
		return service.modifyPage(page);
	}

	// http://localhost:9090/cms-app/page -GET
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Page> viewAllPages() {
		return service.viewAllPages();
	}
	
	@GetMapping(path = "byContent/{content}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Page> findByPageContent(@PathVariable("content") String content) throws NoSuchPageException {
		return service.findPageByContent(content);
	}
	
	
}
