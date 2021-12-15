package com.example.currencylayer.demo.list.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import com.example.currencylayer.demo.list.Valuta;

public class JSONParser {
	// tutti i possibili metodi che prendono info dall'Api ES: EUR
	private final String api_key = "b1478ef56e2d58d7c531d961989d2db3";
	
	public String getApi_key() {
		return api_key;
	}

	private String[] Endpoint= {"list" , "live","historical?date=%04d-%02d-%02d "};

	public Valuta getValuefromApi(String code) { // GBP EUR
		JSONObject obj = JsonFromApi();
		// System.out.println(obj);
		// System.out.println("\n");

		Valuta value = new Valuta(code);
		try {

			JSONObject currenciesObj = obj.getJSONObject("currencies");
			String description = currenciesObj.getString(code);
			// String c=code+dest;//USDEUR
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
		// System.out.println(obj);
		String description= (String) obj.getString(Code);
		file_input.close();
		value.setDescription(description);
		return value;
	}

	// @param nomeFile dove salvare
	public void salvaSuFile(String nomeFile) {
		JSONObject obj = JsonFromApi();
		try {
			PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter(nomeFile)));
			file_output.println(obj);
			file_output.close();
			System.out.println("File salvato!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("static-access")
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
			String end=Endpoint[2].format("historical?date=%04d-%02d-%02d" ,year,month,days);
			url="http://api.currencylayer.com/"+end + "?access_key=" + api_key;;
		}
		RestTemplate rt = new RestTemplate();
		obj = new JSONObject(rt.getForObject(url, String.class));
		return obj;
	}

}
