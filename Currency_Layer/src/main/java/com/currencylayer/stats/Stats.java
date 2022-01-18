package com.currencylayer.stats;

import java.util.HashMap;

import com.currencylayer.exception.SameCurrencyException;
import com.currencylayer.parse.DataParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;


/**
 * class that handles statistics
 */
public class Stats implements StatsInterface{

	private ArrayList<String> days = new ArrayList<String>();

	
	public Stats()
	{
		super();
	}

	/**
	 * gets informations on the value over time of the currencies specified
	 *
	 * @param bas currency to use as unit of measurement
	 * @param opt name of the informations to get in a comma separated list, accepted values: average,variance,minimum,maximum,fluct-per,fluct-abs
	 * @param cur currency to get statistics about in a comma separated list
	 * @param std start date of the measurements
	 * @param end end date of the measurements
	 */
	@Override
	public HashMap<String, Object> createMap(String currency, String base, String[] options, LocalDate startDate, LocalDate endDate)
	{
		
		LocalDate day = startDate;
		LocalDate limit = endDate.plusDays(1);

		while(! day.equals(limit)){
			this.days.add("week/"+day.format(DateTimeFormatter.ISO_LOCAL_DATE));
			day = day.plusDays(1);
		}
	
		HashMap<String, Object> res = new HashMap<String, Object>();
		if(endDate.compareTo(startDate)>0) {
			if (currency.equals(base)) throw new SameCurrencyException("Non puoi inserire due valute uguali!");
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
			if(req.equals("fluct-per")){
				res.put("fluct-per", fluctuation(currency, base, true));
			}
			if(req.equals("fluct-abs")){
				res.put("fluct-abs", fluctuation(currency, base, false));
			}
		}
		
		}
			
		else res.put("error", null);
		return res;
		} 
				
	

	double curValue(String currency, String base, String day)
	{
		DataParser parser = new DataParser();
		double unit;
		double value = 0;
		
			try {
				unit = 1/parser.getCurrencyfromFile(day, base).getExchange_rate();
				value = (1/parser.getCurrencyfromFile(day, currency).getExchange_rate())/unit;
			} catch (IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return value;
	}

	/**
	 * computes average value of a currency over the given time frame
	 * 
	 * @param currency three-characters code of the currency to measure
	 * @param base three-characters code of the currency to use as unit of measurement
	 */
	@Override
	public double average(String currency, String base)
	{
		
		double acc = 0;
		int j=0;
		for(String d : this.days) {

			acc += curValue(currency, base, d);
			j++;
		}
				

		
		return acc/j;
	}
	/**
	 * computes variance of a currency's value over the given time frame
	 * 
	 * @param currency three-characters code of the currency to measure
	 * @param base three-characters code of the currency to use as unit of measurement
	 */
	@Override
	public double variance(String currency, String base){
		double avg = this.average(currency, base);
		double acc = 0;
		double tmp;
		int j=0;
		for(String d : this.days) {
			tmp = (curValue(currency, base, d) - avg);
			acc += (tmp * tmp);
			j++;
		}
				
			
	
		return acc/j;
	}
	/**
	 * finds the minimum value reached by the currency within the given timeframe
	 * 
	 * @param currency three-characters code of the currency to measure
	 * @param base three-characters code of the currency to use as unit of measurement
	 */
	@Override
	public double minimum(String currency, String base){
		double tmp;
		double acc=0;
		int j=0;
		for(String d : this.days) {
			tmp = curValue(currency, base, d);
			if(j == 0 || tmp < acc) {
				acc = tmp;
				j++;
			}
		
		}
		return acc;
	}
	/**
	 * finds the maximum value reached by the currency within the given timeframe
	 * 
	 * @param currency three-characters code of the currency to measure
	 * @param base three-characters code of the currency to use as unit of measurement
	 */
	@Override
	public double maximum(String currency, String base){
		double tmp;
		double acc=0;
		int i=0;
		for(String d : this.days) {
			tmp = curValue(currency, base, d);
			if(i == 0 || tmp > acc){
				acc = tmp;
				i++;
			}
			
		}
		
		return acc;
	}
	/**
	 * lists fluctuations of the given currency's value.
	 *<p>
	 * the first element of the array is the value at the start.<br/>
	 * the other elements are the fluctuations of value,<br/>
	 * expressed either as absolute values or percentages<br/>
	 *</p>
	 * @param currency three-characters code of the currency to measure
	 * @param base three-characters code of the currency to use as unit of measurement
	 */
	@Override
	public ArrayList<Double> fluctuation(String currency, String base, boolean percent){
		double value = 0;
		double prev = 0;
		int count = 0;
		ArrayList<Double> res = new ArrayList<Double>();
			for(String d : this.days) {
				value = curValue(currency, base, d);
				if(count > 0)
					res.add(Double.valueOf(percent ? ((value-prev)*100/prev):(value-prev)));
				else
					res.add(Double.valueOf(value));
				prev = value;
				count++;
			}
		return res;
	}


}
