package com.currencylayer.exception;

import java.time.ZonedDateTime;


import java.time.ZonedDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Luca Pigliacampo
 * @author Mario Maio
 * @author Emanuele Partemi
 * 
 * 
 * This class handles different types of personalizaed exception,
 * by creating different istances of ExceptionModel objects.
 */


@ControllerAdvice
public class Handler {
	
	/**
	 * 
	 * @param e is the exception type handled
	 * @return a ResponseEntity with the personalized parameters
	 *
	 */
	
	@ExceptionHandler(value = {CurrencyNotFoundException.class})
	public ResponseEntity<ExceptionModel> handleCurrencyNotFoundException(CurrencyNotFoundException e){
		
		ExceptionModel er=new ExceptionModel (e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());

		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(value = {DateErrorException.class})
	public ResponseEntity<ExceptionModel> handleDateErrorException(DateErrorException e){
		HttpStatus badRequest=HttpStatus.BAD_REQUEST;
		ExceptionModel er=new ExceptionModel (e.getMessage(),badRequest, ZonedDateTime.now());
		
		return new ResponseEntity<>(er, badRequest);
		
	}

	@ExceptionHandler (value= {AmountFormatException.class})
	public ResponseEntity<ExceptionModel> handleAmountFormaatException(AmountFormatException e){

		ExceptionModel er=new ExceptionModel (e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());

		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

	}
	
		@ExceptionHandler (value= {SameCurrencyException.class})
	public ResponseEntity<ExceptionModel> handleSameCurrencyException(SameCurrencyException e){

		ExceptionModel er=new ExceptionModel (e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());

		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

	}
}
