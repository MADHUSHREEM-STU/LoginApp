package com.abc.login;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.login.controller.UserController;

@SpringBootTest
class LoginApplicationTests {

	@Autowired
	private UserController userController;
	
	
	@Test
	void contextLoads() {
		assertThat(userController).isNotNull();
	}

}
