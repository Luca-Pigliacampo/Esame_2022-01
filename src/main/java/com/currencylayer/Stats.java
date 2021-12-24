package com.currencylayer;

import java.util.HashMap;
import java.util.Scanner;

import com.currencylayer.parse.JSONParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class Stats implements StatsInterface{

	private boolean empty;

	private ArrayList<JSONObject> days = new ArrayList<JSONObject>();
	
	private String[] day= {"2021-12-24","2021-12-23","2021-12-22","2021-12-21","2021-12-20","2021-12-19","2021-12-18"};

	public Stats()
	{
		this.empty = true;
	}

	/**
	 * @param bas currency to use as unit of measurement
	 * @param opt name of the informations to get in a comma separated list, accepted values: average,variance,minimum,maximum
	 * @param cur currency to get statistics about in a comma separated list
	 * @param std start date of the measurements
	 * @param end end date of the measurements
	 */
	public HashMap<String, Double> createMap(String currency, String base, String[] options, LocalDate startDate, LocalDate endDate)
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
		
		HashMap<String, Double> res = new HashMap<String, Double>();
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

		return res;
	}

	private double average(String currency, String base)
	{
		double acc = 0;
		double unit;
		int i=0;
		for( i = 0; i < days.size(); i++){
			if(base.length() == 3 && currency.length() == 3 && day[i].equals(days.get(i).getString("date"))){
				unit = 1/days.get(i).getJSONObject("quotes").getDouble("USD" + currency.toUpperCase());
				acc += (days.get(i).getJSONObject("quotes").getDouble("USD" + base.toUpperCase()))*unit;
			}
			else if(!day[i].equals(days.get(i).getString("date"))) return acc/i;
		}
		return acc/i;
	}
	private double variance(String currency, String base){
		double avg = this.average(currency, base);
		double acc = 0;
		double unit;
		double tmp;
		int i=0;
		for(i = 0; i < days.size(); i++){
			if(base.length() == 3 && currency.length() == 3 && day[i].equals(days.get(i).getString("date"))){
				unit = 1/days.get(i).getJSONObject("quotes").getDouble("USD" + base.toUpperCase());
				tmp = (((1/days.get(i).getJSONObject("quotes").getDouble("USD" + currency.toUpperCase()))/unit) - avg);
				acc += (tmp * tmp);
			}
			else break;
		}
		return acc/i;
	}
	private double minimum(String currency, String base){
		double tmp;
		double acc=0;
		double unit;
		;
		for(int i = 0; i < days.size(); i++){
			if(base.length() == 3 && currency.length() == 3 && day[i].equals(days.get(i).getString("date"))){
				unit = 1/days.get(i).getJSONObject("quotes").getDouble("USD" + base.toUpperCase());
				tmp = (1/days.get(i).getJSONObject("quotes").getDouble("USD" + currency.toUpperCase()))/unit;
				if(i == 0 || tmp < acc)
					acc = tmp;
			}
			else break;
		}
		return acc;
	}
	private double maximum(String currency, String base){
		double tmp;
		double acc=0;
		double unit;
		for(int i = 0; i < days.size(); i++){
			if(base.length() == 3 && currency.length() == 3 && day[i].equals(days.get(i).getString("date"))){
				unit = 1/days.get(i).getJSONObject("quotes").getDouble("USD" + base.toUpperCase());
				tmp = (1/days.get(i).getJSONObject("quotes").getDouble("USD" + currency.toUpperCase()))/unit;
				if(i == 0 || tmp > acc)
					acc = tmp;
			}
			else break;
		}
		return acc;
	}


}
