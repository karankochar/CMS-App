package com.capgemini.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.User;
import com.capgemini.exceptions.NoSuchUserException;
import com.capgemini.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	//get user by user id from database
	@Override
	public User findUserById(int userId) throws NoSuchUserException {
		try {
			Optional<User> user = repository.findById(userId);
			if (user.get() != null && user.get().getUserId() > 0) {
				return user.get();
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchUserException("User not found.");
		}
		return null;
	}

	//save updated user to the database
	@Override
	public User modifyUser(User user, int userId) throws NoSuchUserException {
		User result = findUserById(userId);
		if (result != null && result.getRole().equals("ROLE_USER")) {
			result.setRole(user.getRole());
			result.setFullName(user.getFullName());
			result.setUserName(user.getUserName());
			result.setEmail(user.getEmail());
			result.setPassword(user.getPassword());
			result.setStatus(user.isStatus());
		} else {
			throw new NoSuchUserException("User not found.");
		}
		return repository.save(result);
	}
}