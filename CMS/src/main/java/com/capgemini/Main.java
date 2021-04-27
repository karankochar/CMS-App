package com.capgemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capgemini.controllers.AdminController;
import com.capgemini.entities.Category;
import com.capgemini.entities.Page;
import com.capgemini.repository.CategoryRepository;
import com.capgemini.repository.UserRepository;

@SpringBootApplication

public class Main {

	public static void main(String[] args) {
		 SpringApplication.run(Main.class, args);
		/*
		 * CategoryRepository repository = context.getBean(CategoryRepository.class);
		 * 
		 * Category category = new Category(); category.setCategoryTitle("Category1");
		 * 
		 * Page page = new Page(); page.setPageTitle("page title");
		 * page.setContent("ncdncoewnvcworencvowe"); page.setCategory(category);
		 * 
		 * Page page2 = new Page(); page2.setPageTitle("page title 2");
		 * page2.setContent("ncdncoewnvcwddsacscdvevwencvowe");
		 * page2.setCategory(category);
		 * 
		 * category.addPage(page2); category.addPage(page);
		 * 
		 * repository.save(category);
		 */
	}

}
