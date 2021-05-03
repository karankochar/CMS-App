package com.capgemini.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {
	// Search pages by category
	@Query("Select p From Page p Where p.category.categoryTitle = :categoryTitle")
	public List<Page> findByPageCategoryTitle(@Param("categoryTitle") String categoryTitle);

	// Search pages by specific category and user
	@Query("Select cu From Page cu Where cu.category.categoryTitle = :categoryTitle And cu.author.fullName = :fullName")
	public List<Page> findByCategoryAndUser(@Param("categoryTitle") String categoryTitle,
			@Param("fullName") String fullName);

	// Search pages added by user
	@Query("Select p From Page p Where p.author.fullName = :fullName")
	public List<Page> findByPageUser(@Param("fullName") String fullName);

	// Search pages by content
	@Query("SELECT c FROM Page c WHERE c.content LIKE %:word%")
	List<Page> findByContent(@Param("word") String content);
	
	@Modifying
	@Transactional
	@Query("Delete from Page p where p.pageId = :pageId")
	public void deletePageById(@Param("pageId")  int pageId);
}
