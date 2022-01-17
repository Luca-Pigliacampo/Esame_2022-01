package com.currencylayer.exception;

/**
 * 
 * @author Mario Maio
 * @author Emanuele Partemi
 * @author Luca Pigliacampo
 * 
 * This class extends RunTimeExceptions. It is thrown when 
 * the parser cannot find the JSONObject "currencies", either in API or file.
 * Handled by Handler.java
 */

public class CurrencyNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CurrencyNotFoundException () {
		super ();
		
	}
	
	public CurrencyNotFoundException(String message) {
		super (message);
		
	}
}
