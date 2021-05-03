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
import com.capgemini.exceptions.InvalidUserDetailsExceptions;
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

	public User addUser(User user) throws InvalidUserDetailsExceptions {
		User result = null;
		if (user.getRole().equals("ROLE_USER") &&  user.getEmail().matches("[a-zA-Z0-9_]+@[a-zA-Z_]+(.com|.in)")
				&& user.getPassword().length() >= 6) {
			result = userRepository.save(user);
		} else {
			throw new InvalidUserDetailsExceptions("Please enter valid details !");
		}
		return result;
	}

	public User addAdmin(User user) throws InvalidUserDetailsExceptions {
		User result = null;
		if (user.getRole().equals("ROLE_ADMIN") && user.getEmail().matches("[a-zA-Z0-9_]+@[a-zA-Z_]+(.com|.in)")
				&& user.getPassword().length() >= 6) {
			result = userRepository.save(user);
		} else {
			throw new InvalidUserDetailsExceptions("Please enter valid details !");
		}
		return result;
	}


	@Override
	public User modifyUser(User user, int userId) throws NoSuchUserException {
		User result = findUserById(userId);
		if(result != null) {
			result.setRole(user.getRole());
			result.setFullName(user.getFullName());
			result.setUserName(user.getUserName());
			result.setEmail(user.getEmail());
			result.setPassword(user.getPassword());
			result.setStatus(user.isStatus());
		}
		else {
			throw new NoSuchUserException("User not found");
		}
		return userRepository.save(result);
	}

	@Override
	public User findUserById(int userId) throws NoSuchUserException {
		try {
			Optional<User> user = userRepository.findById(userId);
			if (user.get() != null && user.get().getUserId() > 0) {
				return user.get();

			}
		} catch (NoSuchElementException e) {
			throw new NoSuchUserException("user Id  not found");

		}
		return null;

	}

	@Override
	public boolean removeUser(int userId) throws NoSuchUserException {
		try {
			User user = findUserById(userId);

			if (user != null && user.getUserId() > 0) {
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

	/*
	 * @Override public User viewUser(String userName) throws NoSuchUserException {
	 * User user1 = new User(); UserDetails currentUser =
	 * loadUserByUsername(user1.getUserName()); try { Optional<User> user =
	 * userRepository.findByUserName(userName); if (currentUser.getUsername() ==
	 * user.get().getUserName()) { return user.get();
	 * 
	 * } } catch (NoSuchElementException e) { throw new
	 * NoSuchUserException("user Id  not found");
	 * 
	 * } return null;
	 * 
	 * }
	 */

}
