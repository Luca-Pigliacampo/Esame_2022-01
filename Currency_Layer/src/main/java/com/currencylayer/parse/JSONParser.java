package com.currencylayer.parse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import com.currencylayer.Currency;
import com.currencylayer.Valuta;

public class JSONParser {
	private final String api_key = "58f9efaf928fe5aba2ef4d27c5334757";
	private String[] Endpoint= {"list" , "live","historical?date=%04d-%02d-%02d "};

	public Valuta getValuefromApi(String code) { // GBP EUR
		JSONObject obj = JsonFromApi(0,LocalDate.now());
		Valuta value = new Valuta(code);
		try {

			JSONObject currenciesObj = obj.getJSONObject("currencies");
			String description = currenciesObj.getString(code);
			value.setDescription(description);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public Valuta getValuefromFile(String path, String Code)
			throws URISyntaxException, MalformedURLException, IOException {
		Valuta value = new Valuta(Code);
		Scanner file_input = new Scanner(new BufferedReader(new FileReader(path)));
		String str = file_input.nextLine();
		JSONObject obj1 = new JSONObject(str);
		JSONObject obj = obj1.getJSONObject("currencies");
		String description= (String) obj.getString(Code);
		file_input.close();
		value.setDescription(description);
		return value;
	}

	// @param nomeFile dove salvare
	public void salvaSuFile(String nomeFile,int i ,LocalDate d) {
		JSONObject obj = JsonFromApi(i,d);
		try {
			PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter(nomeFile)));
			file_output.println(obj);
			file_output.close();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String  data=df.format(d);
			String[] conv= data.split("-");
			year=Integer.parseInt(conv[0]);
			month=Integer.parseInt(conv[1]);
			days=Integer.parseInt(conv[2]);
			String end=String.format("historical?date=%04d-%02d-%02d" ,year,month,days);
			url="http://api.currencylayer.com/"+end + "?access_key=" + api_key;;
		}
		RestTemplate rt = new RestTemplate();
		obj = new JSONObject(rt.getForObject(url, String.class));
		System.out.println(url);
		return obj;
	}
	
	public String getApi_key() {
		return api_key;
	}
	public Currency getCurrencyfromApi(String code,LocalDate d) {
		JSONObject obj;
		JSONObject quotesObj;
		if(d==LocalDate.now() || d==null)
		obj = JsonFromApi(1,LocalDate.now());
		else
			obj=JsonFromApi(2,d);
		String path="valuta.json";
		Currency currency = new Currency();
		Valuta valuta=new Valuta(code);
		try {
			valuta=this.getValuefromFile( path, code);

		    quotesObj = obj.getJSONObject("quotes");
			double reateUSDx = quotesObj.getDouble("USD"+code.toUpperCase());
			
			 currency.setValuta(valuta);
			 currency.setExchange_rate(reateUSDx);
			 Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currency;
	}
	
	public Currency getCurrencyfromFile(String path, String Code)
			throws URISyntaxException, MalformedURLException, IOException {
		String path1="valuta.json";
		JSONObject Obj;
		JSONObject quotesObj;
		Currency currency = new Currency();
		Valuta valuta=new Valuta(Code);
		try {
			Scanner file_input = new Scanner(new BufferedReader(new FileReader(path)));
			String str = file_input.nextLine();
			valuta=this.getValuefromFile( path1, Code);
			file_input.close();

		    Obj = new JSONObject(str);
		    quotesObj=Obj.getJSONObject("quotes");
			double reateUSDx = quotesObj.getDouble("USD"+Code.toUpperCase());
			
			 currency.setValuta(valuta);
			 currency.setExchange_rate(reateUSDx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currency;
	}

}
