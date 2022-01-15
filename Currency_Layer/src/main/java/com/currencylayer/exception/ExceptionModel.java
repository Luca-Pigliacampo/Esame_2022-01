package com.currencylayer.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Luca Pigliacampo
 * @author Emanuele Partemi
 * @author Mario Maio
 * 
 * This class represents the Error model that is created.
 * 
 *
 */

public class ExceptionModel {
	
	private final String message;
	private final HttpStatus httpStat;
	private final ZonedDateTime time;
	
	/**
	 * Constructor for ExceptionModel Class
	 * 
	 * @param message is the personalized message
	 * @param httpStat represents the http status code. 
	 * @param time is the time when the exception is thrown
	 */
	
	public ExceptionModel (String message, HttpStatus httpStat, ZonedDateTime time) {
		this.message=message;
		this.httpStat=httpStat;
		this.time=time;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the httpStat
	 */
	public HttpStatus getHttpStat() {
		return httpStat;
	}

	/**
	 * @return the time
	 */
	public ZonedDateTime getTime() {
		return time;
	}


}
