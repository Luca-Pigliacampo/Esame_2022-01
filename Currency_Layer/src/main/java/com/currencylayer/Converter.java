package com.currencylayer;

/**
 * 
 * @author Mario Maio
 * @author Emanuele Partemi
 * @author Luca Pigliacampo
 * 
 * This interface defines the abstract method conversion.
 * This method is used to convert a certain asset (Currency, raw material, stock)
 * into another one, given an amount.
 *
 */

public interface Converter {
	
	/**
	 * @param src currency, raw material, stock
	 * @param tgt currency, raw material, stock
	 * @param amount represents the amount to convert
	 */
	
	void conversion(String src, String tgt, double amount);

}
