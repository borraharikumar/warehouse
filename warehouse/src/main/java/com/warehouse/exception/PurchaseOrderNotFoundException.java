package com.warehouse.exception;

public class PurchaseOrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6418635008962308431L;
	
	public PurchaseOrderNotFoundException() {
		super();
	}
	
	public PurchaseOrderNotFoundException(String message) {
		super(message);
	}

}
