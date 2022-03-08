package com.abc.login.util;

import java.util.ArrayList;
import java.util.List;

import com.abc.login.dto.ForgetPasswordDto;
import com.abc.login.dto.SignUpDto;
import com.abc.login.entity.UserEntity;

public class EntityModelUtil {
	
	

	private EntityModelUtil() {
		super();
	}

	public static UserEntity userModelToEntity(SignUpDto signUpDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(signUpDto.getId());
		userEntity.setFirstname(signUpDto.getFirstname());
		userEntity.setLastname(signUpDto.getLastname());
		userEntity.setEmail(signUpDto.getEmail());
		userEntity.setPassword(signUpDto.getPassword());
		userEntity.setRole(signUpDto.getRole());
		userEntity.setSecurityQuestion(signUpDto.getSecurityQuestion());
		userEntity.setSecurityAnswer(signUpDto.getSecurityAnswer());
		return userEntity;
	}

	public static SignUpDto userEntityToModel(UserEntity userEntity) {

		SignUpDto signUpDto = new SignUpDto();
		signUpDto.setId(userEntity.getId());
		signUpDto.setFirstname(userEntity.getFirstname());
		signUpDto.setLastname(userEntity.getFirstname());
		signUpDto.setEmail(userEntity.getEmail());
		signUpDto.setPassword(userEntity.getPassword());
		signUpDto.setRole(userEntity.getRole());
		signUpDto.setSecurityQuestion(userEntity.getSecurityQuestion());
		signUpDto.setSecurityAnswer(userEntity.getSecurityAnswer());

		return signUpDto;
	}
	
	public static List<SignUpDto> userEntityToModelList(List<UserEntity> entityList) {
		
		List<SignUpDto> userList = new ArrayList<>();
		
		entityList.forEach(entity -> {
			SignUpDto signUpDto = new SignUpDto();
			signUpDto.setId(entity.getId());
			signUpDto.setFirstname(entity.getFirstname());
			signUpDto.setLastname(entity.getFirstname());
			signUpDto.setEmail(entity.getEmail());
			signUpDto.setPassword(entity.getPassword());
			signUpDto.setRole(entity.getRole());
			userList.add(signUpDto);
			
		});
		
		return userList;
	}
	
	public static ForgetPasswordDto passwordEntityToModel(UserEntity userEntity) {

		ForgetPasswordDto forgetPasswordDto = new ForgetPasswordDto();
		forgetPasswordDto.setEmail(userEntity.getEmail());
		forgetPasswordDto.setSecurityQuestion(userEntity.getSecurityQuestion());
		forgetPasswordDto.setSecurityAnswer(userEntity.getSecurityAnswer());
		return forgetPasswordDto;
	}
	
	public static UserEntity passwordModelToEntity(ForgetPasswordDto forgetPasswordDto) {

		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(forgetPasswordDto.getEmail());
		userEntity.setSecurityQuestion(forgetPasswordDto.getSecurityQuestion());
		userEntity.setSecurityAnswer(forgetPasswordDto.getSecurityAnswer());
		return userEntity;
	}
	
}
