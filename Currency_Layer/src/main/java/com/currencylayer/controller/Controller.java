package com.currencylayer.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

import com.currencylayer.Conversion;
import com.currencylayer.Currency;
import com.currencylayer.Pair;
import com.currencylayer.exception.AmountFormatException;
import com.currencylayer.exception.CurrencyNotFoundException;
import com.currencylayer.exception.DateErrorException;
import com.currencylayer.exception.ExceptionModel;
import com.currencylayer.parse.JSONParser;

import com.currencylayer.Stats;

/**
 * 
 * @author Luca Pigliacampo
 * @author Mario Maio
 * @author Emanuele Partemi
 * 
 * This class handle the HTTP requests sended by the user, for a specific root.
 * A ResponseEntity object is returned with the requested data formatted in Json model.
 *
 */

@RestController
public class Controller {
	
	/**
	 * 
	 * @param src
	 * @param tgt
	 * @param amount
	 * @return ResponseEntity
	 * 
	 * This root allows the user to convert two or more currencies.
	 * As it is developed, it allows to convert one currency with another one or more 
	 * (for example 1 EUR can be converted in USD, GBP and CAD).
	 * 
	 */

	@GetMapping(value="/conversion", params= {"src","tgt","amount"})
	public ResponseEntity<Conversion[]> getvalue(@RequestParam String[] src, @RequestParam String[] tgt,@RequestParam double amount) throws URISyntaxException, MalformedURLException, IOException {
		Conversion[] conv=new Conversion[src.length*tgt.length];
		int k=0;
		
		for(int i=0;i<src.length;i++) {
			for(int j=0;j<tgt.length;j++) {

				
				conv[k]=new Conversion();
				conv[k].conversion(src[i], tgt[j], amount);
				k=k+1;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}

		return ResponseEntity.ok(conv);	

	}	
}
	/**
	 * 
	 * @param src
	 * @param tgt
	 * @return ResponseEntity
	 * 
	 * This root returns the live exchange of a given pair. 
	 * 
	 */

	@GetMapping (value="/live", params= {"src", "tgt"})
	public Map<String,Object> getLiveExchange(@RequestParam String src, @RequestParam String tgt) throws CurrencyNotFoundException, DateErrorException, URISyntaxException, IOException{
		Conversion conv=new Conversion();
		return conv.JsonModel(src,tgt);

		
	}
	
	/**
	 * 
	 * 
	 * @param cur Currency 1
	 * @param bas Currency 2
	 * @param opt options (average, minimum, variance, maximum)
	 * @param std starting date
	 * @param end end date
	 * @return ResponseEntity
	 * 
	 * This root allows the user to obtain average, minimum, variance, maximum
	 * for a given pair (cur/bas), in a given period (starting date, end date).
	 */

	@GetMapping(value="/stat", params={"cur","bas","opt","std","end"})
	public ResponseEntity<HashMap<String,HashMap<String,Object>>> getStats(
			@RequestParam String[] cur,
			@RequestParam String bas,
			@RequestParam String[] opt,
			@RequestParam String std,
			@RequestParam String end) throws CurrencyNotFoundException, DateErrorException, URISyntaxException
	{
		HashMap<String,HashMap<String,Object>> res = new HashMap<String,HashMap<String,Object>>();
		Stats stObj = new Stats();
		try {
			for(String moneta : cur){
				res.put(moneta, stObj.createMap(
						moneta,
						bas,
						opt,
						LocalDate.parse(std.trim()),
						LocalDate.parse(end.trim())));
			}
		}
		catch (DateTimeParseException e) {
			throw new DateErrorException("Errore nel formato della data. Il formato corretto è YYYY-MM-DD.");
		}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ResponseEntity.ok(res);
	}

}
