package com.currencylayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.currencylayer.exception.AmountFormatException;
import com.currencylayer.exception.CurrencyNotFoundException;
import com.currencylayer.parse.JSONParser;

class ExceptionTest {
	

	
	@Test
	void test2() {
		Conversion c=new Conversion();
		AmountFormatException exception = assertThrows(AmountFormatException.class, ()->{c.conversion ("GBP", "USD", -1);
		});
		
		assertEquals("Inserisci un valore positivo", exception.getMessage());
	}

	@Test
	void test() {
		
		JSONParser j= new JSONParser();
		
		CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, ()->{j.getValuefromApi("EUT");
		});
		
		assertEquals("Errore: La valuta inserita non esiste", exception.getMessage());
	}
	
	


}
