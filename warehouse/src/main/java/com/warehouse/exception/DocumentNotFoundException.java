package com.warehouse.exception;

public class DocumentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 148410717839949124L;
	
	public DocumentNotFoundException() {
		super();
	}
	
	public DocumentNotFoundException(String message) {
		super(message);
	}

}
