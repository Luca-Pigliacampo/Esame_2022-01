package com.currencylayer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface ConvertedProd {

	/**
	 * 
	 * @param src   la sigla della moneta di partenza (Es. EUR)
	 * @param tgt   la sigla della moneta di arrivo
	 * @param amount quantità da convertire
	 * @throws IOException 
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 * @throws DateErrorException 
	 */
	void conversion(String src, String tgt, double amount);
			
}
