package com.currencylayer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.currencylayer.exception.CurrencyNotFoundException;
import com.currencylayer.exception.DateErrorException;
import com.currencylayer.parse.JSONParser;

class JSONParserTest {

	@Test
	void testGetValuefromFile() throws MalformedURLException, CurrencyNotFoundException, IOException, URISyntaxException {
		JSONParser jj=new JSONParser();

		Currency curr=new Currency("EUR", "Euro");

		assertEquals(jj.getValuefromApi("EUR"), curr, "TEST FALLITO");
	}
	
		@Test 
	void testPairFromApi() throws MalformedURLException, URISyntaxException, IOException {
		JSONParser j2=new JSONParser();
		Pair pair=new Pair("EUR", "USD");
		
		System.out.println(j2.getPairfromApi("EUR", LocalDate.now()));
		
		assertEquals(j2.getPairfromApi("EUR", LocalDate.now()), pair, " TEST FALLITO ");
	}
	
	@AfterEach
	void tearDown() {
	}
}
