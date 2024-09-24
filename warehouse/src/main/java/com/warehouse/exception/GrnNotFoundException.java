package com.warehouse.exception;

public class GrnNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4693601153831947222L;
	
	public GrnNotFoundException() {
		super();
	}
	
	public GrnNotFoundException(String message) {
		super(message);
	}

}
