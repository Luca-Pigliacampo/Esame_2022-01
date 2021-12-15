package com.example.currencylayer.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import org.json.JSONObject;

//import org.springframework.web.client.RestTemplate;

import com.example.currencylayer.demo.list.parser.Conversion;

public class CoversionNotFromUSD {
	private Conversion currency;
	private double amount;
	private double result;
	
	
	public Conversion getCurrency() {
		return currency;
	}
	public void setCurrency(Conversion currency) {
		this.currency = currency;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void conversion(Conversion src,double amount) {
		this.amount=amount;
		JSONObject obj;
		/*String url = "http://api.currencylayer.com/live" + "?access_key=b1478ef56e2d58d7c531d961989d2db3" ;
		RestTemplate rt = new RestTemplate();*/
		String path="C:\\Users\\parte\\OneDrive\\Desktop\\response_live.json";
		String str ;
		String date;
		try {
			Scanner file_input = new Scanner(new BufferedReader(new FileReader(path)));	  
			str= file_input.nextLine();
			
			
				obj =new JSONObject(str);	 //parse JSON Object
				
		
			file_input.close();


			JSONObject quotesObj = obj.getJSONObject("quotes");
			double reateUSDx = quotesObj.getDouble("USD"+src.getTgtCur());
			double rateUSDy =1/(quotesObj.getDouble("USD"+src.getSrcCur()));
			this.currency.setExchangeRate(reateUSDx*rateUSDy);
			this.result=this.currency.getExchangeRate()*amount;	
			Date timeStampDate = new Date((long)(obj.getLong("timestamp")*1000)); 
			 LocalDateTime a=Instant.ofEpochMilli(timeStampDate.getTime())
				      .atZone(ZoneId.of("Europe/Rome"))
				      .toLocalDateTime();
			 DateTimeFormatter formatter = DateTimeFormatter.
		                ofPattern("yyyy-MM-dd",Locale.ITALY).withZone(ZoneId.of("Europe/Rome"));
			 date=a.format(formatter);
			 
	            this.currency.setData(date) ;
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	@Override
	public String toString() {
		return "CoversionNotFromUSD [currency=" + currency + ", amount=" + amount + ", result=" + result + "]";
	}
	
	

}
