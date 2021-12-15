package com.example.currencylayer.demo.list;

import java.util.Objects;


//@Entity
public class Valuta {
	private String code;
	private String description;
	
	public Valuta() {
		super();
	}
	
	public Valuta(String code,String description) {
		  this.code = code;
		  this.description = description;
	  }


	public Valuta(String code2) {
		// TODO Auto-generated constructor stub
		this.code=code2;
		this.description=null;
	}
	public String getTy() {
		return code;
	}

	public void setTy(String id) {
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
		Valuta other = (Valuta) obj;
		return Objects.equals(code, other.code) && Objects.equals(description, other.description);
	}


}
