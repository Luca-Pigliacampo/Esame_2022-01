package com.currencylayer.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ExceptionModel {
	
	private final String message;
	private final HttpStatus httpStat;
	private final ZonedDateTime time;
	
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
