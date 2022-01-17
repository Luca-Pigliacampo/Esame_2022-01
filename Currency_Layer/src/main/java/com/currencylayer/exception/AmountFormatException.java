package com.currencylayer.exception;


/**
 * 
 * @author Mario Maio
 * @author Emanuele Partemi
 * @author Luca Pigliacampo
 * 
 * This class extends RunTimeExceptions. It is thrown when 
 * the amount inserted contains minus symbol. 
 * Handled by Handler.java
 */

public

public class AmountFormatException extends RuntimeException {
	
	private static final long serialVersionUID=3L;
	
	public AmountFormatException() {
		super();
	}
	
	public AmountFormatException(String message) {
		super (message);
	}
	
}
