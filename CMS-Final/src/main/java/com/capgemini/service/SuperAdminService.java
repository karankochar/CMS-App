package com.capgemini.service;

import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserRoleException;

public interface SuperAdminService {
	public User addAdmin(User user) throws InvalidUserRoleException;
}