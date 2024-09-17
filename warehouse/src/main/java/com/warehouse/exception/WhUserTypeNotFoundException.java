package com.warehouse.exception;

public class WhUserTypeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6552296565374186463L;
	
	public WhUserTypeNotFoundException() {
		super();
	}
	
	public WhUserTypeNotFoundException(String message) {
		super(message);
	}

}
