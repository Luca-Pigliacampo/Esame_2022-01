package com.currencylayer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.currencylayer.parse.JSONParser;

public class LiveConversion {


	private Pair currency1;
	private Pair currency2;
	private double exchange_rate;
	private final int AMOUNT=1;
	private LocalDate date;

	public LiveConversion() {

		this.currency1=currency1;
		this.currency2=currency2;
	}



	public void conversion(String currency1, String currency2) {
		JSONParser a=new JSONParser();
		LocalDate d;
		a.saveOnFile("live.json", 1, LocalDate.now());
		try {
			this.currency1=a.getCurrencyfromFile("live.json", currency1.toUpperCase());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.currency2=a.getCurrencyfromFile("live.json", currency2.toUpperCase());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double rateUSDx =this.currency1.getExchange_rate();
		double rateUSDy =1/this.currency2.getExchange_rate();
		this.exchange_rate=rateUSDx*rateUSDy;
		this.date=date.now();
	}

	/**
	 * @return the currency1
	 */
	public Pair getCurrency1() {
		return currency1;
	}

	/**
	 * @param currency1 the currency1 to set
	 */
	public void setCurrency1(Pair currency1) {
		this.currency1 = currency1;
	}

	/**
	 * @return the currency2
	 */
	public Pair getCurrency2() {
		return currency2;
	}

	/**
	 * @param currency2 the currency2 to set
	 */
	public void setCurrency2(Pair currency2) {
		this.currency2 = currency2;
	}

	/**
	 * @return the exchange_rate
	 */
	public double getExchange_rate() {
		return exchange_rate;
	}

	/**
	 * @param exchange_rate the exchange_rate to set
	 */
	public void setExchange_rate(double exchange_rate) {
		this.exchange_rate = exchange_rate;
	}
	/**
	 * @return the d
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param d the d to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
