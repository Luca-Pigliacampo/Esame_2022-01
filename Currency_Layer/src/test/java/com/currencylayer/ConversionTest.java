package com.currencylayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConversionTest {

	@Test
	void testConversion() {
		Conversion c= new Conversion();
		
		String src="EUR";
		String tgt="USD";
		double amount=1;
		double exchange_rate= 1.1340388567073862;
		
		
		//assertEquals(c.conversion(src, tgt, amount), exchange_rate, "");
	}

}
