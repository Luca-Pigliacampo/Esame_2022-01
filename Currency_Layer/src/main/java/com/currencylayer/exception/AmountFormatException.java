package com.currencylayer.exception;

public class AmountFormatException extends RuntimeException {
	
	private static final long serialVersionUID=3L;
	
	public AmountFormatException() {
		super();
	}
	
	public AmountFormatException(String message) {
		super (message);
	}
	
}
