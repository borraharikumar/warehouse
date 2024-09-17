package com.warehouse.exception;

public class UomNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8300751896126605279L;

	public UomNotFoundException() {
		super();
	}
	
	public UomNotFoundException(String message) {
		super(message);
	}

}
