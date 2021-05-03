package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserDetailsExceptions;
import com.capgemini.exceptions.InvalidUserRoleException;
import com.capgemini.exceptions.NoSuchUserException;

public interface AdminService {
	public User addUser(User user) throws InvalidUserDetailsExceptions;
	public User addAdmin(User user) throws InvalidUserDetailsExceptions;
	 public User findUserById(int userId)throws NoSuchUserException ;
	 public boolean  removeUser(int userId) throws NoSuchUserException;
	 public List<User > findAllUser();
	User modifyUser(User user, int userId) throws NoSuchUserException;
}
