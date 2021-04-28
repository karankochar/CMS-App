package com.capgemini.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Page;
import com.capgemini.exceptions.NoSuchPageException;
import com.capgemini.repository.PageRepository;
import com.capgemini.repository.UserRepository;

@Service
public class PageServiceImpl implements PageService {

	@Autowired
	PageRepository repository;
		
	@Override
	public Page addPage(Page page) {
		return repository.save(page);
	
	}

	

	@Override
	public Page viewPageById(int pageId) throws NoSuchPageException {
		try {
			Optional<Page> page = repository.findById(pageId);
			if(page.get() != null) {
				return page.get();
			}
			
		}catch(NoSuchElementException e) {
			 throw new NoSuchPageException("Page with is " +pageId +" not found");
		}
		return null;
	
		
	}

	@Override
	public List<Page> viewAllPages() {
		return repository.findAll();
	}



	@Override
	public Page modifyPage(Page page) {
	
		return repository.save(page);
	}

	
	@Override
	public Page findByPageId(int pageId) throws NoSuchPageException {
		Optional<Page> page = repository.findById(pageId);
		if (page.get() != null) {
			return page.get();
		} else {
			throw new NoSuchPageException("Page Id " + pageId + " entered, doesn't exist");
		}
	}


	@Override
	public boolean removePage(int pageId) throws NoSuchPageException {
		boolean result = false;
		Page page = findByPageId(pageId);
		if (page != null) {
			repository.delete(page);
			result = true;
			return result;
		} else {
			throw new NoSuchPageException("Page Id " + pageId + " doesn't exist");
		}
	}



	@Override
	public List<Page> findPageByContent(String content) throws NoSuchPageException {
		List<Page> result = repository.findByContentContaining(content);
		System.out.println(result);
		if(result.isEmpty()) {
			throw new NoSuchPageException("No page with content " + content + " found");
		}
		else
			return result;
			
	}

	

	/*
	 * @Override public List<Page> searchPagesAddedByUser() {
	 * 
	 * 
	 * return null; }
	 */
	 


}
