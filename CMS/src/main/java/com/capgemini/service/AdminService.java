package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserRoleException;
import com.capgemini.exceptions.NoSuchUserException;

public interface AdminService {
	public User addUser(User user) throws InvalidUserRoleException;
	public User addAdmin(User user) throws InvalidUserRoleException;
	public User  modifyUser(User user );
	 public User findUserById(int userId)throws NoSuchUserException ;
	 public boolean  removeUser(int userId) throws NoSuchUserException;
	 public List<User > findAllUser();
	//User viewUser(String userName) throws NoSuchUserException;
}
