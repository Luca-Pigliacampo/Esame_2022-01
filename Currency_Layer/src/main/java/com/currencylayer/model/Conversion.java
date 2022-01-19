package com.currencylayer.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;
import com.google.gson.Gson;
import com.currencylayer.exception.AmountFormatException;
import com.currencylayer.exception.CurrencyNotFoundException;
import com.currencylayer.exception.SameCurrencyException;
import com.currencylayer.parse.DataParser;

public class Conversion implements Converter {
	private Pair src;
	private Pair tgt;
	private double amount;
	private double result;
	private double exchange_rate_src_tgt;
	private String date;



	public Conversion() {
		super();

	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Pair getSrc() {
		return src;
	}
	public void setSrc(Pair src) {
		this.src = src;
	}
	public Pair getTgt() {
		return tgt;
	}
	public void setTgt(Pair tgt) {
		this.tgt = tgt;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	/**
	 * 
	 * @param src   initial ISO 4217 currency code
	 * @param tgt   final ISO 4217 currency code
	 * @param amount selected quantity
	 */
	public void conversion(String src,String tgt,double amount) {
		
		this.amount=amount;
		DataParser a=new DataParser();
		try{
			a.saveOnFile("live.json", 1, null);
		
			this.src=a.getCurrencyfromFile("live.json", src.toUpperCase());
		
			this.tgt=a.getCurrencyfromFile("live.json", tgt.toUpperCase());
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		double rateUSDx =this.tgt.getExchange_rate();
		double rateUSDy =1/this.src.getExchange_rate();
		this.exchange_rate_src_tgt=rateUSDx*rateUSDy;
		if (amount>0)
		this.result=this.exchange_rate_src_tgt*this.amount;	
		else throw new AmountFormatException("Inserisci un valore positivo");
		if (src.toUpperCase().equals(tgt.toUpperCase())) {
			throw new SameCurrencyException ("Non puoi inserire due valute uguali!");
		}
		LocalDateTime dat=LocalDateTime.now()
				.atZone(ZoneId.of("Europe/Rome"))
				.toLocalDateTime();
		DateTimeFormatter formatter = DateTimeFormatter.
				ofPattern("yyyy-MM-dd",Locale.ITALY).withZone(ZoneId.of("Europe/Rome"));
		String date=dat.format(formatter);

		this.date=date ;

	}

	/**
	 * 
	 * @param src initial ISO 4217 currency code
	 * @param tgt final ISO 4217 currency code
	 * @return a  Map<String,Object> with the specific exchange_rate from src to tgt
	 */
	public Map<String,Object> JsonModel(String src,String tgt) throws MalformedURLException, URISyntaxException, IOException{
		Conversion b=new Conversion();
		DataParser a=new DataParser();
		b.conversion(src, tgt, 1);
		JSONObject main=null;
		JSONObject main1=null;

		try {
			main=new JSONObject(a.getValuefromFile("valuta.json", src));
			main1=new JSONObject(a.getValuefromFile("valuta.json", tgt));

		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,Object> map=new LinkedHashMap<String, Object>();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> yourHashMap = new Gson().fromJson(main.toString(), HashMap.class);	
		@SuppressWarnings("unchecked")
		HashMap<String, Object> yourHashMap1 = new Gson().fromJson(main1.toString(), HashMap.class);	

		map.put("src", yourHashMap);
		map.put("tgt",yourHashMap1);
		map.put("amount", b.getAmount());
		map.put("exchange_rate_src_tgt", b.getExchange_rate_src_tgt());
		map.put("date", b.getDate());
		
		
		
		return map;


		
		
	}


	
	
	public double getExchange_rate_src_tgt() {
		return exchange_rate_src_tgt;
	}

	public void setExchange_rate_src_tgt(double exchange_rate_src_tgt) {
		this.exchange_rate_src_tgt = exchange_rate_src_tgt;
	}




}
