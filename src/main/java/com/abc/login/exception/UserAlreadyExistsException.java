package com.abc.login.exception;

public class UserAlreadyExistsException extends RuntimeException{
	public UserAlreadyExistsException(String msg){
		super(msg);
	}

}
