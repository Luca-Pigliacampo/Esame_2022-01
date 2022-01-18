package com.currencylayer;

/**
 * a pair of currencies
 */
public class Pair extends Currency{
	
	private double exchange_rate;
	
	
	
	public Pair() {
		super();
	}
	

	public Pair(String code, String description) {
		super(code, description);
		// TODO Auto-generated constructor stub
	}


	public Pair(String code2) {
		super(code2);
		// TODO Auto-generated constructor stub
	}


	public double getExchange_rate() {
		return exchange_rate;
	}
	public void setExchange_rate(double exchange_rate) {
		this.exchange_rate = exchange_rate;
	}
}
