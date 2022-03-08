package com.abc.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotExistingException(Exception e) {		
		return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);	
	}

	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<String> handlePasswordMismatchException(Exception e) {		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> handleUserExistsException(Exception e) {		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);	 
	}
	
	@ExceptionHandler(InvalidRoleException.class)
	public ResponseEntity<String> handleInvalidRoleException(Exception e) {		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);	 
	}
	
	@ExceptionHandler(IncorrectAnswerException.class)
	public ResponseEntity<String> handleIncorrectAnswerException(Exception e) {		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);	 
	}

}
