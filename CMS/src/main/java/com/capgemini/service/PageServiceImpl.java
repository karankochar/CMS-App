package com.capgemini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Page;
import com.capgemini.repository.PageRepository;

@Service
public class PageServiceImpl implements PageService {

	@Autowired
	PageRepository repository;
	
	@Override
	public Page addPage(Page page) {
		return repository.save(page);
	
	}

}
