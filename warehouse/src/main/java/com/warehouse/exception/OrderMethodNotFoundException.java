package com.warehouse.exception;

public class OrderMethodNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1789814003706070970L;
	
	public OrderMethodNotFoundException() {
		super();
	}
	
	public OrderMethodNotFoundException(String message) {
		super(message);
	}

}
