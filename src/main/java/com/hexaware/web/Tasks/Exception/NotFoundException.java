package com.hexaware.web.Tasks.Exception;

public class NotFoundException extends Exception {
	private String message;
	
	public NotFoundException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
