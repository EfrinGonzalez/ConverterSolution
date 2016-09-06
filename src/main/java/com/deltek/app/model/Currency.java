package com.deltek.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency{
	private String base;
	private String date;

	@JsonProperty("rates")
	Object rates;
	 
	public Currency(){
			
		}
	
	public Object getRates() {
			return rates;
	}

	public void setRates(Object rates) {
		this.rates = rates;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String baseCurrency) {
		this.base = baseCurrency;
	}

	@Override
	public String toString() {
		return "Currency [base=" + base + ", date=" + date + ", rates=" + rates + "]";
	}

}
