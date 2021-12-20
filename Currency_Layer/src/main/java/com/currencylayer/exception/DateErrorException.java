package com.currencylayer.exception;

public class DateErrorException extends RuntimeException{
	
	private static final long serialVersionUID = 2L;
	
	public  DateErrorException () {
		super();
	}
		
	public DateErrorException (String message) {
		super(message);
	}
	
}
