package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.Page;
import com.capgemini.exceptions.NoSuchPageException;

public interface PageService {
	//user functions:
	public Page addPage(Page page);//done

	public Page modifyPage(Page page);//done

	public Page viewPageById(int pageId) throws NoSuchPageException; // Done

	public List<Page> viewAllPages();//done
	
	public boolean removePage(int pageId) throws NoSuchPageException;

	Page findByPageId(int pageId) throws NoSuchPageException;
	
	List<Page> findPageByContent(String content) throws NoSuchPageException;
	
	// Admin functions:
	//public List<Page> searchPagesAddedByUser();
	  
}

