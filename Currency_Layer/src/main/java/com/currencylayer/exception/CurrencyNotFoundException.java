package com.currencylayer.exception;



public class CurrencyNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CurrencyNotFoundException () {
		super ();
		
	}
	
	public CurrencyNotFoundException(String message) {
		super (message);
		
	}
}
