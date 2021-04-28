package com.capgemini.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capgemini.entities.MyUserDetails;
import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserRoleException;
import com.capgemini.exceptions.NoSuchUserException;
import com.capgemini.repository.UserRepository;

@Service
public class AdminServiceImpl implements UserDetailsService, AdminService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found" + userName));
		return user.map(MyUserDetails::new).get();
	}

	public User addUser(User user) throws InvalidUserRoleException {
		User result = null;
		if (user.getRole().equals("ROLE_USER")) {
			result = userRepository.save(user);
		} else {
			throw new InvalidUserRoleException("User with role " + user.getRole() + " cannot be added");
		}
		return result;
	}
	
	public User addAdmin(User user) throws InvalidUserRoleException {
		User result = null;
		if (user.getRole().equals("ROLE_ADMIN")) {
			result = userRepository.save(user);
		} else {
			throw new InvalidUserRoleException("User with role " + user.getRole() + " cannot be added");
		}
		return result;
	}

	@Override
	public User modifyUser(User user) {
		return userRepository.save(user);

	}

	@Override
	public User findUserById(int userId) throws NoSuchUserException {
		try {
		Optional<User> user = userRepository.findById(userId);
		if (user.get() != null) {
			return user.get();

		}} catch(NoSuchElementException e) {
			throw new NoSuchUserException("user Id  not found");

		}
		return null;

	}

	@Override
	public boolean removeUser(int userId) throws NoSuchUserException {
		try {
			User user = findUserById(userId);

			if (user != null) {
				userRepository.delete(user);
				return true;
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchUserException("user Id not found");

		}
		return false;
	}

	@Override
	public List<User> findAllUser() {

		return userRepository.findAll();
	}
	
	

}
