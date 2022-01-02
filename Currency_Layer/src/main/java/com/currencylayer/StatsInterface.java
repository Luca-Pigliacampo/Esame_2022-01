package com.currencylayer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;

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

	double average(String currency, String base);

	double variance(String currency, String base);

	double minimum(String currency, String base);

	double maximum(String currency, String base);

	public ArrayList<Double> fluctuation(String currency, String base, boolean percent);
}
