package com.capgemini.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Scope(scopeName = "prototype")
@Entity
@Table(name = "page_table")
public class Page implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "page_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pageId;

	@Column(name = "page_title")
	private String pageTitle;

	@Column(name = "page_content")
	private String content;

	@ManyToOne(/* cascade = CascadeType.ALL, */ fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;

	@Autowired
	@ManyToOne(/* cascade = CascadeType.ALL, */ fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User author;

	public Page() {
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	@JsonIgnore
	public Category getCategory() {
		return category;
	}

	@JsonIgnore
	public void setCategory(Category category) {
		this.category = category;
	}

	@JsonIgnore
	public User getAuthor() {
		return author;
	}

	@JsonIgnore
	public void setAuthor(User author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCategory_id() {
		return category.getCategoryId();
	}

	// getter Method to get the author's full name
	public String getCategoryTitle() {
		return category.getCategoryTitle();
	}

	@Override
	public String toString() {
		return "Page [pageId=" + pageId + ", pageTitle=" + pageTitle + ", category=" + category + ", author=" + author
				+ ", content=" + content + "]";
	}

}
