package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.entities.User;
import com.capgemini.exceptions.InvalidUserRoleException;

@SpringBootTest
class SuperAdminServiceImplTest {

	@Autowired
	private SuperAdminService service;

	@Test
	void testSuperAdminShouldAddAnotherAdmin() throws InvalidUserRoleException {

		User admin = new User();

		admin.setFullName("Deepak");
		admin.setUserName("deep181");
		admin.setEmail("deep171@gmail.com");
		admin.setPassword("12345");
		admin.setStatus(true);
		admin.setRole("ROLE_ADMIN");

		User expected = service.addAdmin(admin);
		assertNotNull(expected);

	}

}