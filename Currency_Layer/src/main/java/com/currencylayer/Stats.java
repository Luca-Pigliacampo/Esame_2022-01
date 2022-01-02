package com.currencylayer;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Stream;

import com.currencylayer.parse.JSONParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;

public class Stats implements StatsInterface{

	private ArrayList<JSONObject> days = new ArrayList<JSONObject>();

	private String[] help;
	public Stats()
	{
		super();
	}

	/**
	 * @param bas currency to use as unit of measurement
	 * @param opt name of the informations to get in a comma separated list, accepted values: average,variance,minimum,maximum
	 * @param cur currency to get statistics about in a comma separated list
	 * @param std start date of the measurements
	 * @param end end date of the measurements
	 */
	@Override
	public HashMap<String, Object> createMap(String currency, String base, String[] options, LocalDate startDate, LocalDate endDate)
	{
		/*LocalDate limit = startDate.minusDays(1);
		LocalDate day = endDate;
		JSONParser jp = new JSONParser();
		if(this.empty){
			while(! day.equals(limit)){
				if(day.equals(LocalDate.now())){
					this.days.add(jp.JsonFromApi(1, day));
				}else{
					this.days.add(jp.JsonFromApi(2, day));
				}
				day = day.minusDays(1);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.empty = false;
		}*/
		Scanner file_input = null ;
		try {
			file_input = new Scanner(new BufferedReader(new FileReader("WEEK.json")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str=file_input.nextLine();
		JSONArray main=new JSONArray(str);
		for(int i=0;i<7;i++) {
			days.add(main.getJSONObject(i));
		}
		Object[] news=startDate.datesUntil(endDate.plusDays(1)).toArray();
		this.help=new String[news.length];
		for (int i=0;i<help.length;i++) help[i]=news[i].toString();
		
	

		
		
		HashMap<String, Object> res = new HashMap<String, Double>();
		if(endDate.compareTo(startDate)>0) {
		for(String req : options){
			if(req.equals("average")){
				res.put("average", Double.valueOf(this.average(currency, base)));
			}
			if(req.equals("variance")){
				res.put("variance", Double.valueOf(this.variance(currency, base)));
			}
			if(req.equals("minimum")){
				res.put("minimum", Double.valueOf(this.minimum(currency, base)));
			}
			if(req.equals("maximum")){
				res.put("maximum", Double.valueOf(maximum(currency, base)));
			}
		}
		
		}
		else res.put("error", null);

				return res;
	}

	@Override
	public double average(String currency, String base)
	{
		double acc = 0;
		double unit;
		int i=0;
		int j=0;
		for(String date:this.help) {
			for(i=0;i<days.size();i++) {
			if(days.get(i).getString("date").equals(date)) {
				unit = days.get(i).getJSONObject("quotes").getDouble("USD" + base.toUpperCase());
				acc += 1/((days.get(i).getJSONObject("quotes").getDouble("USD" + currency.toUpperCase()))/unit);
				j++;
			}
				
			}
		}

		
		return acc/j;
	}
	@Override
	public double variance(String currency, String base){
		double avg = this.average(currency, base);
		double acc = 0;
		double unit;
		double tmp;
		int i=0;
		int j=0;
		for(String date:this.help) {
			for(i=0;i<days.size();i++) {
			if(days.get(i).getString("date").equals(date) && base.length() == 3 && currency.length() == 3) {
				unit = 1/days.get(i).getJSONObject("quotes").getDouble("USD" + base.toUpperCase());
				tmp = (((1/days.get(i).getJSONObject("quotes").getDouble("USD" + currency.toUpperCase()))/unit) - avg);
				acc += (tmp * tmp);
				j++;
				
			}
				
			}
		}
				
			
	
		return acc/j;
	}
	@Override
	public double minimum(String currency, String base){
		double tmp;
		double acc=0;
		double unit;
		int i=0;
		int j=0;
		for(String date:this.help) {
			for(i=0;i<days.size();i++) {
			if(days.get(i).getString("date").equals(date) && base.length() == 3 && currency.length() == 3) {
				unit = 1/days.get(i).getJSONObject("quotes").getDouble("USD" + base.toUpperCase());
				tmp = (1/days.get(i).getJSONObject("quotes").getDouble("USD" + currency.toUpperCase()))/unit;
				if(j == 0 || tmp < acc) {
					acc = tmp;
					j++;
				}
			}
		
		}
		}
		return acc;
	}
	@Override
	public double maximum(String currency, String base){
		double tmp;
		double acc=0;
		double unit;
		int i=0;
		for(String date:this.help) {
			for(i=0;i<days.size();i++) {
			if(days.get(i).getString("date").equals(date) && base.length() == 3 && currency.length() == 3) {
				unit = 1/days.get(i).getJSONObject("quotes").getDouble("USD" + base.toUpperCase());
				tmp = (1/days.get(i).getJSONObject("quotes").getDouble("USD" + currency.toUpperCase()))/unit;
				if(i == 0 || tmp > acc)
					acc = tmp;
				
			}
		
		}
		}
		return acc;
	}
	@Override
	public ArrayList<Double> fluctuation-percent(String currency, String base){
		double value;
		int count = 0;
		double unit;
		for(String date : this.help) {
			for(i=0;i<days.size();i++) {
				if(days.get(i).getString("date").equals(date) && base.length() == 3 && currency.length() == 3) {
					
				}
			}
		}
	}


}
