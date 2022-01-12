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
	@AfterEach
	void tearDown() {
	}
}
