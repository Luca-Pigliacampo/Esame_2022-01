package com.example.currencylayer.demo.list.parser;

public class Currency {
  private Valuta valuta;
	private double exchange_rate;
	private String date;
	
	
	public Currency() {
		super();
	}
	
	public Valuta getValuta() {
		return valuta;
	}
	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	}
	public double getExchange_rate() {
		return exchange_rate;
	}
	public void setExchange_rate(double exchange_rate) {
		this.exchange_rate = exchange_rate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
