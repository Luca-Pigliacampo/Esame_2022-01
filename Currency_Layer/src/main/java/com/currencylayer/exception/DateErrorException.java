package com.currencylayer.exception;

/**
 * 
 * @author Mario Maio
 * @author Emanuele Partemi
 * @author Luca Pigliacampo
 * 
 * This class extends RunTimeExceptions. It is thrown when 
 * some errors in the format date (YYYY-MM-DD) occure.
 * Handled by Handler.java
 */

public class DateErrorException extends RuntimeException{
	
	private static final long serialVersionUID = 2L;
	
	public  DateErrorException () {
		super();
	}
		
	public DateErrorException (String message) {
		super(message);
	}
	
}
