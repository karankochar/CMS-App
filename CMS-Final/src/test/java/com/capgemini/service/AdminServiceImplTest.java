package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserDetailsExceptions;
import com.capgemini.exceptions.NoSuchUserException;

@SpringBootTest
public class AdminServiceImplTest {

	@Autowired
	AdminService service;

	@Test
	void testFindUserByIdShouldReturnUser() throws InvalidUserDetailsExceptions, NoSuchUserException {
		User user = new User();
		user.setFullName("testname11");
		user.setUserName("testttt111111");
		user.setEmail("testtt12t@test.com");
		user.setPassword("test123411");
		user.setRole("ROLE_USER");
		user.setStatus(true);

		User expected = service.addUser(user);
		User actual = service.findUserById(expected.getUserId());

		assertEquals(expected.getUserId(), actual.getUserId());
		assertEquals(expected.getUserName(), actual.getUserName());
	}

	@Test
	void testRemoveUserShouldRemoveUser() throws InvalidUserDetailsExceptions, NoSuchUserException {
		User user = new User();
		user.setFullName("shivam11");
		user.setUserName("shivam11");
		user.setEmail("shiva112m@gmail.com");
		user.setPassword("222224gfg");
		user.setRole("ROLE_USER");
		user.setStatus(true);

		service.addUser(user);

		Boolean actual = service.removeUser(user.getUserId());
		assertEquals(true, actual);

	}

	@Test
	void testFindAllShouldReturnAllUserObjects() throws InvalidUserDetailsExceptions {
		User user1 = new User();
		user1.setFullName("shiva111m");
		user1.setUserName("shivam1fr11");
		user1.setEmail("djvnodnvc1bf111l@gmail.com");
		user1.setPassword("passwordrg11");
		user1.setRole("ROLE_USER");
		user1.setStatus(true);

		User user2 = new User();
		user2.setFullName("deepak");
		user2.setUserName("deepeak1112");
		user2.setEmail("deepakk11rk@gmail.com");
		user2.setPassword("eeeeeeeeeeee");
		user2.setRole("ROLE_USER");
		user2.setStatus(true);

		service.addUser(user1);
		service.addUser(user2);

		List<User> actual = service.findAllUser();
		assertEquals(user2.getFullName(), actual.get(actual.size() - 1).getFullName());
	}

	@Test
	void testAddAdminShouldAddAnotherAdmin() throws NoSuchUserException, InvalidUserDetailsExceptions {
		User admin = new User();

		admin.setFullName("Deepak");
		admin.setUserName("deep11vfgak");
		admin.setEmail("deepa11k@yahoo.com");
		admin.setPassword("12345vdjkvbsd");
		admin.setStatus(true);
		admin.setRole("ROLE_ADMIN");

		User expected = service.addAdmin(admin);

		User actual = service.findUserById(expected.getUserId());
		assertEquals(expected.getUserId(), actual.getUserId());
		assertEquals(expected.getRole(), actual.getRole());

	}

	@Test
	void testAddAdminShouldAddUser() throws NoSuchUserException, InvalidUserDetailsExceptions {
		User user = new User();
		user.setFullName("Anuj");
		user.setUserName("a11n6vdvdsv78eeerrrrrrrrru");
		user.setEmail("rrrrrvd09eerr@gmail.com");
		user.setPassword("123vd45655");
		user.setStatus(true);
		user.setRole("ROLE_USER");

		User expected = service.addUser(user);

		User actual = service.findUserById(expected.getUserId());
		assertEquals(expected.getUserId(), actual.getUserId());
		assertEquals(expected.getRole(), actual.getRole());

	}

	@Test
	void testModifyUserShouldReturnUserObject() throws NoSuchUserException, InvalidUserDetailsExceptions {
		User user = new User();

		user.setFullName("Anuja");
		user.setUserName("Anu11ja2");
		user.setEmail("anuja11a@gmail.com");
		user.setPassword("12345fdafvad6");
		user.setStatus(true);
		user.setRole("ROLE_USER");
		User add = service.addUser(user);

		add.setUserId(user.getUserId());
		add.setPassword("112233dv3");
		add.setUserName("Anuj22avds3");
		User update = service.modifyUser(add, add.getUserId());
		assertEquals(add.getUserId(), update.getUserId());
		// assertEquals(add.getRole(), update.getRole());
		assertEquals(add.getUserName(), update.getUserName());

	}
}
