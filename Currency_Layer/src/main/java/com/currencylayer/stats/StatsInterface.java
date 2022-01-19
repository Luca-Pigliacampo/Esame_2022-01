package com.currencylayer.stats;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * interface describing the Stats endpoint
 */
public interface StatsInterface {

	/**
	 * @param bas currency to use as unit of measurement
	 * @param opt name of the informations to get in a comma separated list, accepted values: average,variance,minimum,maximum
	 * @param cur currency to get statistics about in a comma separated list
	 * @param std start date of the measurements
	 * @param end end date of the measurements
	 */
	HashMap<String, Object> createMap(String currency, String base, String[] options, LocalDate startDate,
			LocalDate endDate);

	/**
	 * computes average value of a currency over the given time frame
	 * 
	 * @param currency three-characters code of the currency to measure
	 * @param base three-characters code of the currency to use as unit of measurement
	 */
	double average(String currency, String base);

	/**
	 * computes variance of a currency's value over the given time frame
	 * 
	 * @param currency three-characters code of the currency to measure
	 * @param base three-characters code of the currency to use as unit of measurement
	 */
	double variance(String currency, String base);

	/**
	 * finds the minimum value reached by the currency within the given timeframe
	 * 
	 * @param currency three-characters code of the currency to measure
	 * @param base three-characters code of the currency to use as unit of measurement
	 */
	double minimum(String currency, String base);

	/**
	 * finds the maximum value reached by the currency within the given timeframe
	 * 
	 * @param currency three-characters code of the currency to measure
	 * @param base three-characters code of the currency to use as unit of measurement
	 */
	double maximum(String currency, String base);

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
	public ArrayList<Double> fluctuation(String currency, String base, boolean percent);
}
