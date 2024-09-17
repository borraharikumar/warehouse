package com.warehouse.exception;

public class ShipmentTypeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1494771188047863307L;
	
	public ShipmentTypeNotFoundException() {
		super();
	}
	
	public ShipmentTypeNotFoundException(String message) {
		super(message);
	}

}
