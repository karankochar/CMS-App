package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Category;
import com.capgemini.entities.Page;
import com.capgemini.entities.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
