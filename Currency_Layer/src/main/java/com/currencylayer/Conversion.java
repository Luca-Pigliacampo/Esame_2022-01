package com.currencylayer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;

import com.currencylayer.parse.JSONParser;

public class Conversion {
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
	 * @param src   la sigla della moneta di partenza (Es. EUR)
	 * @param tgt   la sigla della moneta di arrivo
	 * @param amount quantità da convertire
	 */
	public void conversion(String src,String tgt,double amount) {
		this.amount=amount;
		JSONParser a=new JSONParser();
		a.saveOnFile("live.json", 1, null);
		try {
			this.src=a.getCurrencyfromFile("live.json", src.toUpperCase());
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
			this.tgt=a.getCurrencyfromFile("live.json", tgt.toUpperCase());
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
			double rateUSDx =this.tgt.getExchange_rate();
			double rateUSDy =1/this.src.getExchange_rate();
			this.exchange_rate_src_tgt=rateUSDx*rateUSDy;
			this.result=this.exchange_rate_src_tgt*this.amount;	
			
			 LocalDateTime dat=LocalDateTime.now()
				      .atZone(ZoneId.of("Europe/Rome"))
				      .toLocalDateTime();
			 DateTimeFormatter formatter = DateTimeFormatter.
		                ofPattern("yyyy-MM-dd",Locale.ITALY).withZone(ZoneId.of("Europe/Rome"));
			String date=dat.format(formatter);
			
	            this.date=date ;
		
	}
	public Map conversion(String src,String tgt) {
		this.amount=1;
		JSONParser a=new JSONParser();
		a.saveOnFile("live.json", 1, LocalDate.now());
		try {
			this.src=a.getCurrencyfromFile("live.json", src.toUpperCase());
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
			this.tgt=a.getCurrencyfromFile("live.json", tgt.toUpperCase());
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
			double rateUSDx =this.tgt.getExchange_rate();
			double rateUSDy =1/this.src.getExchange_rate();
			this.exchange_rate_src_tgt=rateUSDx*rateUSDy;
			Map <String, Object> map = new LinkedHashMap<String,Object>();
			Currency currencyA=new Currency(this.src.getCode(), this.src.getDescription());
			Currency currencyB = new Currency(this.tgt.getCode(), this.tgt.getDescription());
			map.put("currency1", currencyA);
			map.put("currency2", currencyB);
			map.put("exchange_rate", this.exchange_rate_src_tgt);
			return map;
			
	}
	
	
	public double getExchange_rate_src_tgt() {
		return exchange_rate_src_tgt;
	}

	public void setExchange_rate_src_tgt(double exchange_rate_src_tgt) {
		this.exchange_rate_src_tgt = exchange_rate_src_tgt;
	}


	

}
