package com.capgemini.service;

import java.util.List;
import java.util.Set;

import com.capgemini.entities.Page;
import com.capgemini.exceptions.NoSuchPageException;
import com.capgemini.exceptions.NoSuchUserException;
import com.capgemini.exceptions.NosuchCategoryFoundException;

public interface PageService {
	//user functions:
	public Page addPage(Page page);//done
	
	public Page AddPage(int userid, int id, Page page) throws NosuchCategoryFoundException, NoSuchUserException ;
		
	public Page modifyPage(int userId ,int categoryid, Page page) throws NosuchCategoryFoundException, NoSuchUserException ;//done

	public Page viewPageById(int pageId) throws NoSuchPageException; // Done

	public List<Page> viewAllPages();//done
	
	public boolean removePage(int pageId) throws NoSuchPageException;

	//Page findByPageId(int pageId) throws NoSuchPageException;
	
	public List<Page> findPageByCategory(String categoryTitle) throws  NosuchCategoryFoundException;
	
	public List<Page> findPageByUser(String fullName) throws NoSuchUserException;
	
	public List<Page> findPageByCategoryAndUser(String categoryTitle, String fullName);
	
	public List<Page> findPageByContent(String content) throws NoSuchPageException;


	
	// Admin functions:
	//public List<Page> searchPagesAddedByUser();
	  
}


