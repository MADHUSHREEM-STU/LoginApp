package com.abc.login.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.login.dto.SignUpDto;
import com.abc.login.entity.UserEntity;
import com.abc.login.exception.UserNotFoundException;
import com.abc.login.repository.UserRepository;
import com.abc.login.util.EntityModelUtil;

@SpringBootTest
class UserServiceImplTest {
	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	UserRepository userRepository;
	
	@Before(value = "")
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test()
	final void testSignUp() {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		userEntity.setFirstname("madhu");
		userEntity.setLastname("shree");
		userEntity.setEmail("mad@gmail.com");
		userEntity.setPassword("madhu");
		userEntity.setRole("admin");
		userEntity.setSecurityQuestion("ur school");
		userEntity.setSecurityAnswer("evergreen");
		
		 when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

		 SignUpDto savedUser = userService.signUp(EntityModelUtil.userEntityToModel(userEntity));
	     assertThat(savedUser.getFirstname()).isNotNull();
	     assertEquals(userEntity.getFirstname(), savedUser.getFirstname());
	}
	
	@Test
	final void testGetUserByEmail() {
		UserEntity userEntity=new UserEntity();
		userEntity.setId(1);
		userEntity.setFirstname("madhu");
		userEntity.setLastname("shree");
		userEntity.setEmail("mad@gmail.com");
		userEntity.setPassword("madhu");
		userEntity.setRole("admin");
		userEntity.setSecurityQuestion("ur school");
		userEntity.setSecurityQuestion("evergreen");
		
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
		SignUpDto signUpDto=userService.getUserByEmail("mad@gmail.com");
		assertNotNull(signUpDto);
		assertEquals("madhu",signUpDto.getFirstname());
	}
	
	
	@Test
	final void testGetUserByEmail_UserNotFountException() {
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		assertThrows(UserNotFoundException.class,()->{
			userService.getUserByEmail("mad@gmail.com");
			});
	}
	
	@Test
	final void testGetAllUsers() {
	    UserEntity userEntity1 = new UserEntity();
	    userEntity1.setId(1);
	    userEntity1.setFirstname("madhu");
	    userEntity1.setLastname("shree");
	    userEntity1.setEmail("mad@gmail.com");
	    userEntity1.setPassword("madhu");
	    userEntity1.setRole("admin");
	    userEntity1.setSecurityQuestion("ur school");
	    userEntity1.setSecurityAnswer("evergreen");
	    
	    
	    UserEntity userEntity2 = new UserEntity();
	    userEntity2.setId(1);
	    userEntity2.setFirstname("kavya");
	    userEntity2.setLastname("shree");
	    userEntity2.setEmail("kavya@gmail.com");
	    userEntity2.setPassword("kavya");
	    userEntity2.setRole("user");
	    userEntity2.setSecurityQuestion("ur school");
	    userEntity2.setSecurityAnswer("pragathi");
	    
	    List<UserEntity> userList = new ArrayList<UserEntity>();
	    userList.add(userEntity1);
	    userList.add(userEntity2);

	    when(userRepository.findAll()).thenReturn(userList);

	    List<SignUpDto> fetchedusers = userService.getAllUsers();
	    assertThat(fetchedusers).hasSizeGreaterThan(1);
	}
	
	
	@Test
	final void deleteUser() {
		int id = 2;
		UserEntity userEntity = new UserEntity();
		userEntity.setId(2);
		userEntity.setFirstname("madhu");
		userEntity.setLastname("shree");
		userEntity.setEmail("mad@gmail.com");
		userEntity.setPassword("madhu");
		userEntity.setSecurityQuestion("ur school");
		userEntity.setSecurityAnswer("evergreen");
		
		Optional<UserEntity> optionalUser = Optional.of(userEntity);
		
		when(userRepository.findById(id)).thenReturn(optionalUser);

		
		userService.deleteUser(userEntity.getId());
		
		verify(userRepository,times(1)).deleteById(id);
		
		doNothing().when(userRepository).deleteById(userEntity.getId());	
	}
	

}
