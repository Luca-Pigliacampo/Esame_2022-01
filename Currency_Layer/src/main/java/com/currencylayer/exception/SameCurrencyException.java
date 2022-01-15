package com.currencylayer.exception;

public class SameCurrencyException extends RuntimeException{
	
	private static final long serialVersionUID = 4L;
	
	public  SameCurrencyException () {
		super();
	}
		
	public SameCurrencyException (String message) {
		super(message);
	}

}
