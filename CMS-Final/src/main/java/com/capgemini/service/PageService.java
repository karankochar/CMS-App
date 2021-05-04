package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.Page;
import com.capgemini.exceptions.NoSuchPageException;
import com.capgemini.exceptions.NoSuchUserException;
import com.capgemini.exceptions.NosuchCategoryFoundException;

public interface PageService {

	public Page addPage(int userid, int id, Page page) throws NosuchCategoryFoundException, NoSuchUserException;

	public Page modifyPage(int userId, int categoryid, Page page)
			throws NosuchCategoryFoundException, NoSuchUserException;

	public Page viewPageById(int pageId) throws NoSuchPageException;

	public List<Page> viewAllPages();

	public boolean removePage(int pageId) throws NoSuchPageException;

	public List<Page> findPageByCategory(String categoryTitle) throws NosuchCategoryFoundException;

	public List<Page> findPageByUser(String fullName) throws NoSuchUserException;

	public List<Page> findPageByCategoryAndUser(String categoryTitle, String fullName) throws NoSuchPageException;

	public List<Page> findPageByContent(String content) throws NoSuchPageException;

	List<Page> findPageByUserId(int userId) throws NoSuchUserException;
}
