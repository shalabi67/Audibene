package com.shalabi.exceptions;

public class MissingDataException extends RuntimeException {
	private static final long serialVersionUID = 269128851422178267L;

	public MissingDataException() {
		super();
	}

	public MissingDataException(String message) {
		super(message);
	}
	
	

}
