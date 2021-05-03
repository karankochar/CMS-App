package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.User;
import com.capgemini.exceptions.NoSuchUserException;

public interface UserService {

	public User findUserById(int userId) throws NoSuchUserException;

	public User modifyUser(User user, int userId) throws NoSuchUserException;

}