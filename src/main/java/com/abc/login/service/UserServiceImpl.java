package com.abc.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.login.dto.ApiResponse;
import com.abc.login.dto.ForgetPasswordDto;
import com.abc.login.dto.LoginDto;
import com.abc.login.dto.SignUpDto;
import com.abc.login.entity.UserEntity;
import com.abc.login.exception.IncorrectAnswerException;
import com.abc.login.exception.InvalidRoleException;
import com.abc.login.exception.PasswordMismatchException;
import com.abc.login.exception.UserAlreadyExistsException;
import com.abc.login.exception.UserNotFoundException;
import com.abc.login.repository.UserRepository;
import com.abc.login.util.EntityModelUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	
	/****************************************************************************************************************************
	 - Method Name      : signUp
	 - Input Parameters : SignUpDto signUpDto
	 - Return type      : SignUpDto
	 - Author           : Madhushree M
	 - Creation Date    : 06-03-2022
	 - Description      : Registering user details into  the database.
	  ****************************************************************************************************************************/
	
	@Override
	public SignUpDto signUp(SignUpDto signUpDto) throws UserAlreadyExistsException {
		UserEntity userEntity = null;
		List<UserEntity> list = userRepository.findUsersByEmail(signUpDto.getEmail());
		if (!(list.isEmpty())) {
			throw new UserAlreadyExistsException(" Oops!  There is already a user registered with this email.");
		} else {
			userEntity = userRepository.save(EntityModelUtil.userModelToEntity(signUpDto));
		}

		return EntityModelUtil.userEntityToModel(userEntity);
	}
	
	/****************************************************************************************************************************
	 - Method Name      : login
	 - Input Parameters : LoginDto loginDto
	 - Return type      : LoginDto
	 - Author           : Madhushree M
	 - Creation Date    : 06-03-2022
	 - Description      : Login using the registered details.
	  ****************************************************************************************************************************/
	
	@Override
	public ApiResponse login(LoginDto loginDto)
			throws UserNotFoundException, PasswordMismatchException, InvalidRoleException {

		UserEntity user = userRepository.findByEmail(loginDto.getEmail());
		if (user == null) {
			throw new UserNotFoundException("User does not exist with this email id.");
		}
		if (!user.getPassword().equals(loginDto.getPassword())) {
			throw new PasswordMismatchException("Password mismatch.");
		}
		if (!user.getRole().equals(loginDto.getRole())) {
			throw new InvalidRoleException("no user registered with this role ");
		}
		return new ApiResponse(200, "Login sucess", "u have successfully logged in:");
	}
	
	/****************************************************************************************************************************
	 - Method Name      : forgetPassword
	 - Input Parameters : ForgetPasswordDto forgetPasswordDto
	 - Return type      : ApiResponse
	 - Author           : Madhushree M
	 - Creation Date    : 06-03-2022
	 - Description      : condition for forget password.
	  ****************************************************************************************************************************/

	@Override
	public ApiResponse forgetPassword(ForgetPasswordDto forgetPasswordDto)
			throws IncorrectAnswerException, UserNotFoundException {

		UserEntity user = userRepository.findByEmail(forgetPasswordDto.getEmail());
		if (user == null) {
			throw new UserNotFoundException("Sorry! User is not existing with this Email ");
		}
		if (!user.getSecurityAnswer().equals(forgetPasswordDto.getSecurityAnswer())) {
			throw new IncorrectAnswerException("answer is incorrect.");
		}

		return new ApiResponse(200, "you can reset password", " security question answered correctly");
	}
	
	/****************************************************************************************************************************
	 - Method Name      : updatePassword
	 - Input Parameters : signUpDto signUpDto
	 - Return type      : SignUpDto
	 - Author           : Madhushree M
	 - Creation Date    : 06-03-2022
	 - Description      : updating the information.
	  ****************************************************************************************************************************/
	
	@Override
	public SignUpDto updatePassword(SignUpDto signUpDto) throws UserNotFoundException {

		UserEntity user = userRepository.findByEmail(signUpDto.getEmail());

		if (user == null) {
			throw new UserNotFoundException("User does not exist with this email id.");
		}

		UserEntity updatedUserEntity = userRepository.save(EntityModelUtil.userModelToEntity(signUpDto));

		return EntityModelUtil.userEntityToModel(updatedUserEntity);
	}
	
	/****************************************************************************************************************************
	 - Method Name      : getUserByEmail
	 - Input Parameters : String email
	 - Return type      : SignUpDto
	 - Author           : Madhushree M
	 - Creation Date    : 06-03-2022
	 - Description      : fetching user information using email.
	  ****************************************************************************************************************************/

	@Override
	public SignUpDto getUserByEmail(String email) throws UserNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UserNotFoundException("Sorry! User is not existing with this Email ");
		}
		return EntityModelUtil.userEntityToModel(userEntity);
	}
	
	/****************************************************************************************************************************
	 - Method Name      : getAllUsers
	 - Return type      : List<SignUpDto>
	 - Author           : Madhushree M
	 - Creation Date    : 06-03-2022
	 - Description      : fetching all users information.
	  ****************************************************************************************************************************/

	@Override
	public List<SignUpDto> getAllUsers() {

		return EntityModelUtil.userEntityToModelList(userRepository.findAll());
	}
	
	/****************************************************************************************************************************
	 - Method Name      : deleteUser
	 - Input Parameters : int id
	 - Return type      : void
	 - Author           : Madhushree M
	 - Creation Date    : 06-03-2022
	 - Description      : deleting the user by id.
	  ****************************************************************************************************************************/

	@Override
	public void deleteUser(int id) throws UserNotFoundException {

		Optional<UserEntity> optionalUserEntity = userRepository.findById(id);

		if (optionalUserEntity.isPresent()) {
			userRepository.deleteById(id);
		} else {
			throw new UserNotFoundException("Sorry! User is not existing with id: " + id);
		}
	}

}
