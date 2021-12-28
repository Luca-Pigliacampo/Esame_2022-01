package com.currencylayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.currencylayer.exception.CurrencyNotFoundException;

class ExceptionTest {

	@BeforeEach
	void setUp() throws Exception {
		Currency c=new Currency();
	}

	@AfterEachddd
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertThrows(CurrencyNotFoundException.class, ()->)
	}

}
