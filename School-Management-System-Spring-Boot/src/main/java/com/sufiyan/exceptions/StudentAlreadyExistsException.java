package com.sufiyan.exceptions;

public class StudentAlreadyExistsException extends RuntimeException {

	/**
	 * Author:
	 * Sufiyan Ahmad
	 */
	private static final long serialVersionUID = 1L;
	
	public StudentAlreadyExistsException(String message) {
        super(message);
    }

}
