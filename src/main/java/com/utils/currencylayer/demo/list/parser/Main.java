package com.example.currencylayer.demo.list.parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import com.example.currencylayer.demo.list.Valuta;

public class Main {

	public static void main(String[] args) throws MalformedURLException, URISyntaxException, IOException {
		// TODO Auto-generated method stub
		JSONParser a=new JSONParser();
		Valuta b=a.getValuefromFile("C:\\Users\\parte\\OneDrive\\Desktop\\currencylayer_list.json","ALL");
		System.out.println(b.toString());

	}

}
