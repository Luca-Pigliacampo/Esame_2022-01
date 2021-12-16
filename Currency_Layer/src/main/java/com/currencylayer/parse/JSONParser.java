package com.currencylayer.parse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import com.currencylayer.Currency;
import com.currencylayer.Pair;



public class JSONParser {
	private final String api_key = "3d4885c32c9a655712aff09b44c7ccf6";
	private String[] Endpoint= {"list" , "live","historical?date=%04d-%02d-%02d "};

	/**
	 * 
	 * @param code sigla moneta
	 * @return	oggetto currency corrispondente
	 */
	public Currency getValuefromApi(String code) { // GBP EUR
		JSONObject obj = JsonFromApi (0,LocalDate.now()); //Prende il JSON object presente sull'endpoint "list"
		Currency currency = new Currency(code); //istanza dell'oggetto currency con il code corrispondente
		try {
			JSONObject currenciesObj = obj.getJSONObject("currencies"); 
			String description = currenciesObj.getString(code);
			currency.setDescription(description);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currency;
	} //da chiedere se togliere

	public Currency getValuefromFile(String path, String Code)
			throws URISyntaxException, MalformedURLException, IOException {
		Currency currency = new Currency(Code);
		Scanner file_input = new Scanner(new BufferedReader(new FileReader(path)));
		String str = file_input.nextLine();//Prende l'intero JSON come stringa 
		JSONObject obj1 = new JSONObject(str);//lo converte in JSON obj
		JSONObject obj = obj1.getJSONObject("currencies");//associa l'oggetto JSON corrispondente alla chiave currencies
		String description= obj.getString(Code);
		file_input.close();
		currency.setDescription(description);
		return currency;
	}

	// @param nomeFile dove salvare
	public void saveOnFile(String nomeFile,int i ,LocalDate d) {
		JSONObject obj = JsonFromApi(i,d);
		try {
			PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter(nomeFile)));
			file_output.println(obj);
			file_output.close();
			System.out.println("File salvato!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public JSONObject JsonFromApi(int i,LocalDate d) {
		JSONObject obj;
		String url = "http://api.currencylayer.com/"+Endpoint[i] + "?access_key=" + api_key;
		int year,month,days;
		if(i==2) {
			DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd");//Crea un oggetto "data" e lo formatta con il pattern stabilito 
			String  data=df.format(d);
			String[] conv= data.split("-");// separa la data dove trova "-", e ne crea un Array
			year=Integer.parseInt(conv[0]); 
			month=Integer.parseInt(conv[1]);
			days=Integer.parseInt(conv[2]);
			String end=String.format("historical?date=%04d-%02d-%02d" ,year,month,days);
			url="http://api.currencylayer.com/"+end + "?access_key=" + api_key;
		}
		RestTemplate rt = new RestTemplate();
		obj = new JSONObject(rt.getForObject(url, String.class));
		System.out.println(url);
		return obj;
	}

	public String getApi_key() {
		return api_key;
	}
	public Pair getPairfromApi(String code,LocalDate d) {
		JSONObject obj;
		JSONObject quotesObj;
		if(d==LocalDate.now() || d==null)
			obj = JsonFromApi(1,LocalDate.now());
		else
			obj=JsonFromApi(2,d);
		String path="valuta.json";
		Pair pair = new Pair();
		Currency currency=new Currency();
		try {
			currency=this.getValuefromFile( path, code);

			quotesObj = obj.getJSONObject("quotes");
			double reateUSDx = quotesObj.getDouble("USD"+code.toUpperCase());

			pair.setCode(currency.getCode());
			pair.setDescription(currency.getDescription());
			pair.setExchange_rate(reateUSDx);
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pair;
	}

	public Pair getCurrencyfromFile(String path, String Code)
			throws URISyntaxException, MalformedURLException, IOException {
		String path1="valuta.json";
		JSONObject Obj;
		JSONObject quotesObj;
		Pair pair = new Pair();
		Currency currency=new Currency(Code);
		try {
			Scanner file_input = new Scanner(new BufferedReader(new FileReader(path)));
			String str = file_input.nextLine();
			currency=this.getValuefromFile( path1, Code);
			file_input.close();

			Obj = new JSONObject(str);
			quotesObj=Obj.getJSONObject("quotes");
			double reateUSDx = quotesObj.getDouble("USD"+Code.toUpperCase());

			pair.setCode(currency.getCode());
			pair.setDescription(currency.getDescription());
			pair.setExchange_rate(reateUSDx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pair;
	}

}
