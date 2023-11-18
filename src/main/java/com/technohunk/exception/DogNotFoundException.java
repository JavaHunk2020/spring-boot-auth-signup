package com.technohunk.exception;
//This is a custom exception
//this is unchecked exception
public class DogNotFoundException extends RuntimeException{
	
	public DogNotFoundException(String message){
		super(message);
	}
}
