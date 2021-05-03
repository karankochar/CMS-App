package com.capgemini.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserRoleException;
import com.capgemini.repository.UserRepository;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {
	@Autowired
	private UserRepository repository;

	@Override
	public User addAdmin(User user) throws InvalidUserRoleException {
		User result = null;

		if (user.getRole().equals("ROLE_ADMIN")) {

			result = repository.save(user);

		} else {

			throw new InvalidUserRoleException("User with role " + user.getRole() + " cannot be added");

		}
		return result;
	}

}