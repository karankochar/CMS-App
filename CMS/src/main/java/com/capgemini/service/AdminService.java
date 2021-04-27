package com.capgemini.service;

import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserRoleException;

public interface AdminService {
	public User addUser(User user) throws InvalidUserRoleException;
	public User addAdmin(User user) throws InvalidUserRoleException;
}
