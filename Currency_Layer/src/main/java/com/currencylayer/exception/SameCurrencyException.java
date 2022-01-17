package com.currencylayer.exception;

/**
 * 
 * @author Mario Maio
 * @author Emanuele Partemi
 * @author Luca Pigliacampo
 * 
 * This class extends RunTimeExceptions. It is thrown when 
 * the user inserts the same currency object.
 * Handled by Handler.java
 */

public class SameCurrencyException extends RuntimeException{
	
	private static final long serialVersionUID = 4L;
	
	public  SameCurrencyException () {
		super();
	}
		
	public SameCurrencyException (String message) {
		super(message);
	}

}
