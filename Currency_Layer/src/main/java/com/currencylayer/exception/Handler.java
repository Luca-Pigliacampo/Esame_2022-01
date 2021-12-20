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

		ExceptionModel er=new ExceptionModel (e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());

		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = {DateErrorException.class})
	public ResponseEntity<ExceptionModel> handleDateErrorExceptionException(CurrencyNotFoundException e){

		ExceptionModel er=new ExceptionModel (e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());

		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler (value= {AmountFormatException.class})
	public ResponseEntity<ExceptionModel> handleAmountFormaatException(CurrencyNotFoundException e){

		ExceptionModel er=new ExceptionModel (e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());

		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

	}
}
