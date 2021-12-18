package com.currencylayer;

import java.util.HashMap;
import com.currencylayer.parse.JSONParser;
import java.time.LocalDate;
import org.json.JSONObject;
import java.util.ArrayList;

public class Stats{

	private boolean empty;

	private ArrayList<JSONObject> days = new ArrayList<JSONObject>();

	public Stats()
	{
		this.empty = true;
	}

	public HashMap<String, Double> createMap(String currency, String base, String[] options, LocalDate startDate, LocalDate endDate)
	{
		LocalDate limit = startDate.minusDays(1);
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
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.empty = false;
		}
		HashMap<String, Double> res = new HashMap();
		for(String req : options){
			if(req.equals("average")){
				res.put("average", new Double(this.average(currency, base)));
			}
			if(req.equals("variance")){
				res.put("variance", new Double(this.variance(currency, base)));
			}
			if(req.equals("minimum")){
				res.put("minimum", new Double(this.minimum(currency, base)));
			}
			if(req.equals("maximum")){
				res.put("maximum", new Double(this.maximum(currency, base)));
			}
		}

		return res;
	}

	private double average(String currency, String base)
	{
		double acc = 0;
		double unit;
		for(int i = 0; i < days.size(); i++){
			if(base.length() == 3 && currency.length() == 3){
				unit = 1/days.get(i).getJSONObject("quotes").getDouble("USD" + base.toUpperCase());
				acc += (1/days.get(i).getDouble("USD" + currency.toUpperCase()))/unit;
			}
		}
		return acc/7;
	}
	private double variance(String currency, String base){
		double avg = this.average(currency, base);
		double acc = 0;
		double unit;
		double tmp;
		for(int i = 0; i < days.size(); i++){
			if(base.length() == 3 && currency.length() == 3){
				unit = 1/days.get(i).getJSONObject("quotes").getDouble("USD" + base.toUpperCase());
				tmp = (((1/days.get(i).getJSONObject("quotes").getDouble("USD" + currency.toUpperCase()))/unit) - avg);
				acc += (tmp * tmp);
			}
		}
		return acc/7;
	}
	private double minimum(String currency, String base){
		double tmp;
		double acc=0;
		double unit;
		for(int i = 0; i < days.size(); i++){
			if(base.length() == 3 && currency.length() == 3){
				unit = 1/days.get(i).getJSONObject("quotes").getDouble("USD" + base.toUpperCase());
				tmp = (1/days.get(i).getJSONObject("quotes").getDouble("USD" + currency.toUpperCase()))/unit;
				if(i == 0 || tmp < acc)
					acc = tmp;
			}
		}
		return acc;
	}
	private double maximum(String currency, String base){
		double tmp;
		double acc=0;
		double unit;
		for(int i = 0; i < days.size(); i++){
			if(base.length() == 3 && currency.length() == 3){
				unit = 1/days.get(i).getJSONObject("quotes").getDouble("USD" + base.toUpperCase());
				tmp = (1/days.get(i).getJSONObject("quotes").getDouble("USD" + currency.toUpperCase()))/unit;
				if(i == 0 || tmp > acc)
					acc = tmp;
			}
		}
		return acc;
	}
}
