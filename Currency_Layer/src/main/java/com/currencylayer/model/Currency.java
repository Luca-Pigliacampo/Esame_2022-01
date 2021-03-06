package com.currencylayer.model;

import java.util.Objects;

/**
 * class used to represent currencies
 */
public class Currency {
	private String code; 
	private String description;
	
	public Currency() {
		super();
	}
	
	public Currency(String code,String description) {
		  this.code = code;
		  this.description = description;
	  }


	public Currency(String code2) {
		// TODO Auto-generated constructor stub
		this.code=code2;
		this.description=null;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String id) {
		this.code = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Valuta [code=" + code + ", description=" + description + "]";
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Currency other = (Currency) obj;
		return Objects.equals(code, other.code) && Objects.equals(description, other.description);
	}



}
