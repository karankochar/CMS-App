package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserDetailsExceptions;
import com.capgemini.exceptions.InvalidUserRoleException;
import com.capgemini.exceptions.NoSuchUserException;

// this is the adminservice interface here we are defining the methods which we are going to perform into the admin module
public interface AdminService {
	public User addUser(User user) throws  InvalidUserDetailsExceptions;   // admin can add or create user
	public User addAdmin(User user) throws  InvalidUserDetailsExceptions;  // admin can add or create admin
	 public User findUserById(int userId)throws NoSuchUserException ; //admin can read other userdetails through their id
	 public boolean  removeUser(int userId) throws NoSuchUserException;// admin can remove or delete other user through id
	 public List<User > findAllUser();                                 // admin can retrieve all the users
	User modifyUser(User user, int userId) throws NoSuchUserException;
}
