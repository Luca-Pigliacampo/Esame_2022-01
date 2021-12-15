package com.example.currencylayer.demo.list;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import com.example.currencylayer.demo.list.parser.Conversion;
import com.example.currencylayer.demo.list.parser.JSONParser;

public class Historical {
	
	public JSONArray getWeek() {
		JSONArray week=new JSONArray(7);
		JSONParser a=new JSONParser();
		JSONObject obj;
		DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now=LocalDate.now();
		for(int i=0;i<7;i++) {
		String date=df.format(now.minusDays(i));
		String url = "http://api.currencylayer.com/historical" + "?access_key=" + a.getApi_key()+"&date="+ date;
		RestTemplate rt = new RestTemplate();
		obj = new JSONObject(rt.getForObject(url, JSONObject.class));
		week.put(i,obj);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return week;
		
	}

}
