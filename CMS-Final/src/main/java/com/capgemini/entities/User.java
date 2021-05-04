package com.capgemini.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
@Entity
@Table(name = "user_table")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "role", nullable = false, length = 50)
	private String role;

	@Column(name = "full_name", nullable = false, length = 50)
	private String fullName;

	@Column(name = "user_name", nullable = false, length = 20, unique = true)
	private String userName;

	@Column(name = "email", unique = true, length = 50, nullable = false)
	private String email;

	@Column(name = "password", nullable = false, length = 50)
	private String password;

	@Column(name = "status")
	private boolean status;

	@OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<Page> page = new HashSet<>();

	// default contructor
	public User() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<Page> getPage() {
		return page;
	}

	public void setPage(Set<Page> page) {
		this.page = page;
	}

	public void addPage(Page page) {
		page.setAuthor(this); // this will avoid nested cascade
		this.getPage().add(page);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", role=" + role + ", fullName=" + fullName + ", userName=" + userName
				+ ", email=" + email + ", password=" + password + ", status=" + status + ", page=" + page + "]";
	}

}
