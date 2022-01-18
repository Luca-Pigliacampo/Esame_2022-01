package com.currencylayer.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.currencylayer.exception.CurrencyNotFoundException;
import com.currencylayer.model.Currency;
import com.currencylayer.parse.DataParser;

class testParser {

	private DataParser jj;

	@BeforeEach
	void setUp() throws Exception {
		jj=new DataParser();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws CurrencyNotFoundException, URISyntaxException {
		DataParser jj=new DataParser();

		Currency curr=new Currency("EUR", "Euro");

		assertEquals(jj.getValuefromApi("EUR"), curr, "TEST FALLITO");
	}

	

}
