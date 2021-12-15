package com.example.currencylayer.demo.list.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import com.example.currencylayer.demo.list.Valuta;

public class Conversion {
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	private String data;
	private String srcCur;//GBP decide chi usa programma
	private String tgtCur;//EUR
	private double exchangeRate;
	private Valuta srcValuta;
	private Valuta tgtValuta;
	
	public Conversion(String src,String tgt) {
		this.srcCur=src.toUpperCase();
		this.tgtCur=tgt.toUpperCase();
		this.exchangeRate=0;
		//this.data=null;
		JSONParser a=new JSONParser();
		try {
			this.srcValuta=a.getValuefromFile("C:\\Users\\parte\\OneDrive\\Desktop\\response.json", src.toUpperCase());
			this.tgtValuta=a.getValuefromFile("C:\\Users\\parte\\OneDrive\\Desktop\\response.json", tgt.toUpperCase());
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getSrcCur() {
		return srcCur;
	}
	public void setSrcCur(String srcCur) {
		this.srcCur = srcCur;
	}
	public String getTgtCur() {
		return tgtCur;
	}
	public void setTgtCur(String tgtCur) {
		this.tgtCur = tgtCur;
	}
	public double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public Valuta getSrcValuta() {
		return srcValuta;
	}
	public void setSrcValuta(Valuta srcValuta) {
		this.srcValuta = srcValuta;
	}
	public Valuta getTgtValuta() {
		return tgtValuta;
	}
	public void setTgtValuta(Valuta tgtValuta) {
		this.tgtValuta = tgtValuta;
	}
//	public String getData() {
		//return data;
	//}

//	public void setData(String data) {
//		this.data = data;
	//}

	@Override
	public String toString() {
		return "Conversion [srcCur=" + srcCur + ", tgtCur=" + tgtCur + ", exchangeRate=" + exchangeRate + ", srcValuta="
				+ srcValuta.getDescription() + ", tgtValuta=" + tgtValuta.getDescription() + "]";
	}

	
	
	
	
	//metodo conversione ritorna un doble
	//GBPEUR METODO jSONOBJECT SIMIL QUESTO
	
	//1 GBP= 0,78 EUR - 1 pound  corrisponde a 0,78 euro
	

}
