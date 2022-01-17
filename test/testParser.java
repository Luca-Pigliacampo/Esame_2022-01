package com.currencylayer.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.currencylayer.Currency;
import com.currencylayer.Pair;
import com.currencylayer.exception.CurrencyNotFoundException;
import com.currencylayer.parse.*;

class testParser {

	private JSONParser jj;

	@BeforeEach
	void setUp() throws Exception {
		jj=new JSONParser();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws CurrencyNotFoundException, URISyntaxException {
		JSONParser jj=new JSONParser();

		Currency curr=new Currency("EUR", "Euro");

		assertEquals(jj.getValuefromApi("EUR"), curr, "TEST FALLITO");
	}
}
