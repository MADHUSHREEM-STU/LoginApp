package com.abc.login.service;

import java.util.List;

import com.abc.login.dto.ApiResponse;
import com.abc.login.dto.ForgetPasswordDto;
import com.abc.login.dto.LoginDto;
import com.abc.login.dto.SignUpDto;



public interface UserService {
	
	public SignUpDto signUp(SignUpDto signUpDto);
	
	public ApiResponse login(LoginDto loginDto);
	
	public ApiResponse forgetPassword(ForgetPasswordDto forgetPasswordDto);
	
	public SignUpDto updatePassword(SignUpDto signUpDto);

	public SignUpDto getUserByEmail(String email);
	
	public List<SignUpDto> getAllUsers();
	
	public void deleteUser(int id);

	
}