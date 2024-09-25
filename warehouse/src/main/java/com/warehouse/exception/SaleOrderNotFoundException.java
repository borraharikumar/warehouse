package com.warehouse.exception;

public class SaleOrderNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -4187340357772998139L;

	public SaleOrderNotFoundException() {
		super();
	}
	
	public SaleOrderNotFoundException(String message) {
		super(message);
	}

}
