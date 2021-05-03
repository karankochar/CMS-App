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
		user.setFullName("testname");
		user.setUserName("test");
		user.setEmail("testt@test.com");
		user.setPassword("test1234");
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
		user.setFullName("shivam");
		user.setUserName("shivam");
		user.setEmail("shivam@gmail.com");
		user.setPassword("password");
		user.setRole("ROLE_USER");
		user.setStatus(true);

		service.addUser(user);

		Boolean actual = service.removeUser(user.getUserId());
		assertEquals(true, actual);

	}

	@Test
	void testFindAllShouldReturnAllUserObjects() throws InvalidUserDetailsExceptions {
		User user1 = new User();
		user1.setFullName("shivam");
		user1.setUserName("shivam");
		user1.setEmail("djvnodnvcl@gmail.com");
		user1.setPassword("password");
		user1.setRole("ROLE_USER");
		user1.setStatus(true);

		User user2 = new User();
		user2.setFullName("deepak");
		user2.setUserName("deepak12");
		user2.setEmail("deepakkk@gmail.com");
		user2.setPassword("password");
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
		admin.setUserName("deepak");
		admin.setEmail("deepak@yahoo.com");
		admin.setPassword("123456");
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
		user.setUserName("anu");
		user.setEmail("anu@gmail.com");
		user.setPassword("12345");
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
		user.setUserName("Anuja2");
		user.setEmail("anujaa@gmail.com");
		user.setPassword("123456");
		user.setStatus(true);
		user.setRole("ROLE_USER");
		User add = service.addUser(user);

		add.setUserId(user.getUserId());
		add.setPassword("1122333");
		add.setUserName("Anuja3");
		User update = service.modifyUser(add, add.getUserId());
		assertEquals(add.getUserId(), update.getUserId());
		// assertEquals(add.getRole(), update.getRole());
		assertEquals(add.getUserName(), update.getUserName());

	}
}
