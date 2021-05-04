package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserDetailsExceptions;
import com.capgemini.exceptions.NoSuchUserException;

@SpringBootTest
public class UserServiceImplTest {

	@Autowired
	AdminService adminService;

	@Autowired
	UserService userService;

	@Test
	void testFindUserByIdShouldReturnUser() throws InvalidUserDetailsExceptions, NoSuchUserException {
		User user = new User();
		user.setFullName("ronak singh");
		user.setUserName("ronak");
		user.setEmail("ronak@test.com");
		user.setPassword("test1234");
		user.setRole("ROLE_USER");
		user.setStatus(true);

		User expected = adminService.addUser(user);
		User actual = userService.findUserById(expected.getUserId());

		assertEquals(expected.getUserId(), actual.getUserId());
		assertEquals(expected.getUserName(), actual.getUserName());
	}

	@Test
	public void testModifyUserShouldReturnModifiedUserObject()
			throws InvalidUserDetailsExceptions, NoSuchUserException {
		User user = new User();

		user.setFullName("Anuja");
		user.setUserName("Anuja1");
		user.setEmail("anuja@gmail.com");
		user.setPassword("123456");
		user.setStatus(true);
		user.setRole("ROLE_USER");
		User add = adminService.addUser(user);

		user.setPassword("112233");
		user.setUserName("Anuja2");
		User update = userService.modifyUser(add, add.getUserId());
		assertEquals(add.getUserId(), update.getUserId());
		assertEquals("Anuja2", update.getUserName());
	}

}
