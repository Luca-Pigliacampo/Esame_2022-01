package com.currencylayer.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.currencylayer.Conversion;
import com.currencylayer.exception.AmountFormatException;
import com.currencylayer.exception.CurrencyNotFoundException;
import com.currencylayer.exception.SameCurrencyException;
import com.currencylayer.parse.JSONParser;

class testException {

	private Conversion c, c2;
	private JSONParser j;


	@BeforeEach
	void setUp() throws Exception {
		c=new Conversion();
		c2=new Conversion();
		j=new JSONParser();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		AmountFormatException exception = assertThrows(AmountFormatException.class, ()->{c.conversion ("GBP", "USD", -1);
		});

		assertEquals("Inserisci un valore positivo", exception.getMessage());
	}


	@Test
	void test2() {


		CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, ()->{j.getValuefromApi("EUT");
		});

		assertEquals("La valuta EUT non esiste", exception.getMessage());
	}
	
	@Test
	void test3() {
		SameCurrencyException exception = assertThrows(SameCurrencyException.class, ()->{c2.conversion("EUR", "EUR", 1);
		});

		assertEquals("Non puoi inserire due valute uguali!", exception.getMessage());
		
	}
	

}


