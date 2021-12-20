package com.currencylayer.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {
	
	@ExceptionHandler(value = {CurrencyNotFoundException.class})
	public ResponseEntity<ExceptionModel> handleCurrencyNotFoundException(CurrencyNotFoundException e){
		
		ExceptionModel er=new ExceptionModel (e.getMessage(), HttpStatus.BAD_GATEWAY, ZonedDateTime.now());

		return new ResponseEntity<>(er, HttpStatus.BAD_GATEWAY);

	}
	
	@ExceptionHandler(value = {DateErrorException.class})
	public ResponseEntity<ExceptionModel> handleDateErrorException(DateErrorException e){
		HttpStatus badRequest=HttpStatus.BAD_REQUEST;
		ExceptionModel er=new ExceptionModel (e.getMessage(),badRequest, ZonedDateTime.now());
		
		return new ResponseEntity<>(er, badRequest);
		
	}

}
