package com.warehouse.exception;

public class PartNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7134887921578198741L;
	
	public PartNotFoundException() {
		super();
	}
	
	public PartNotFoundException(String message) {
		super(message);
	}

}
