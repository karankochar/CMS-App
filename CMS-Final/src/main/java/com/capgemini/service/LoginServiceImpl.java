package com.capgemini.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.User;
import com.capgemini.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	UserRepository repository;
	
	@Override
	public User login(User user) {
		User userResult = null;
		
		Optional<User> opt = repository.findByEmail(user.getEmail());
		
		if(opt.isPresent()) {
			userResult = opt.get();
			if(userResult.getPassword().equals(user.getPassword()) && userResult.isStatus()==true) {
				return userResult;
			}else {
				return null;
			}
		}
		
		return userResult;
	}

}
