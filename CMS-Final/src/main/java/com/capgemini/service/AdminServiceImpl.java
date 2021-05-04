package com.capgemini.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capgemini.entities.MyUserDetails;
import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserDetailsExceptions;
import com.capgemini.exceptions.NoSuchUserException;
import com.capgemini.logger.GlobalLogger;
import com.capgemini.repository.UserRepository;

@Service
public class AdminServiceImpl implements UserDetailsService, AdminService {

	@Autowired
	UserRepository userRepository;

	private Logger logger = GlobalLogger.getLogger(CategoryServiceImpl.class);
	private static String log = " called";

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found" + userName));
		return user.map(MyUserDetails::new).get();
	}

	//save user to databse
	public User addUser(User user) throws InvalidUserDetailsExceptions {

		String methodname = " addUser()";
		logger.info(methodname + log);

		User result = null;
		if (user.getRole().equals("ROLE_USER") && user.getEmail().matches("[a-zA-Z0-9_]+@[a-zA-Z_]+(.com|.in)")
				&& user.getPassword().length() >= 6) {
			result = userRepository.save(user);
		} else {
			throw new InvalidUserDetailsExceptions("Please enter valid details !");
		}
		return result;
	}
	
	//save admin to database
	public User addAdmin(User user) throws InvalidUserDetailsExceptions {

		String methodname = " addAdmin()";
		logger.info(methodname + log);

		User result = null;
		if (user.getRole().equals("ROLE_ADMIN") && user.getEmail().matches("[a-zA-Z0-9_]+@[a-zA-Z_]+(.com|.in)")
				&& user.getPassword().length() >= 6) {
			result = userRepository.save(user);
		} else {
			throw new InvalidUserDetailsExceptions("Please enter valid details !");
		}
		return result;
	}

	//save modified user to database
	@Override
	public User modifyUser(User user, int userId) throws NoSuchUserException {

		String methodname = " modifyuser()";
		logger.info(methodname + log);

		User result = findUserById(userId);
		if (result != null) {
			result.setRole(user.getRole());
			result.setFullName(user.getFullName());
			result.setUserName(user.getUserName());
			result.setEmail(user.getEmail());
			result.setPassword(user.getPassword());
			result.setStatus(user.isStatus());
		} else {
			throw new NoSuchUserException("User not found");
		}
		return userRepository.save(result);
	}
	
	//get user by id from database
	@Override
	public User findUserById(int userId) throws NoSuchUserException {

		String methodname = " findUserByIdCalled()";
		logger.info(methodname + log);

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
	
	//deletes user from database
	@Override
	public boolean removeUser(int userId) throws NoSuchUserException {
		String methodname = " removeUser()";
		logger.info(methodname + log);

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

	//get all users from database
	@Override
	public List<User> findAllUser() {
		String methodname = " findAllUser()";
		logger.info(methodname + log);

		return userRepository.findAll();
	}

}
