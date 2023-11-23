package com.technohunk.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GenericExceptionHandler {
	
	@ExceptionHandler(DogNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(DogNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
	

	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handleGenException(AccessDeniedException ex,WebRequest request){
		 ErrorDetails errorDetails = new ErrorDetails(new Date(), "Sorry it seems like you are not authorized to access this resource.", request.getDescription(false));
	        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleGenException(BadCredentialsException ex,WebRequest request){
		 ErrorDetails errorDetails = new ErrorDetails(new Date(), "Sorry sorry , username and password are not correct.", request.getDescription(false));
	        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenException(Exception ex,WebRequest request){
		 ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
	        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
