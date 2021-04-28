package com.capgemini.service;

import java.util.List;
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

	@Override
	public User addUser(User user) {
		return repository.save(user);
	}

	@Override
	public User findUserById(int userId) throws NoSuchUserException {
		try {
			Optional<User> user = repository.findById(userId);
			if (user.get() != null) {
				return user.get();
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchUserException("user Id not found");
		}
		return null;
	}

	@Override
	public User modifyUser(User user) {
		return repository.saveAndFlush(user);
	}

	@Override
	public boolean removeUser(int userId) throws NoSuchUserException {
		try {
			User user = findUserById(userId);
			if (user != null) {
				repository.delete(user);
				return true;
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchUserException("user Id not found");
		}
		return false;
	}

	@Override
	public List<User> findAllUsers() {
		return repository.findAll();
	}

}