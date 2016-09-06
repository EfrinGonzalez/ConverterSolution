package com.deltek.app.converter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.deltek.app.interfaces.ConversionInterface;
import com.deltek.app.model.Currency;

@Component 
public class Converter implements ConversionInterface{
	
	@Autowired
	private RestOperations restOperations;	
	private final String urlCurrency;
	private final String urlNumbers;	
	private BigDecimal currencyRateFrom = null;
	private BigDecimal currencyRateTo = null;
	private BigDecimal multiplicationResult = null;
	private BigDecimal FinalResult = null;
	
	@Autowired
	public Converter(@Value("${currency.service.url}")final String urlCurrency,
					 @Value("${numberstowords.service.url}")final String urlNumbers){
		this.urlNumbers = urlNumbers;
		this.urlCurrency = urlCurrency;
	}
	

	@SuppressWarnings("null")
	@Override
	public BigDecimal convert(BigDecimal amount, String sourceCurrency, String destinationCurrency) {
		Currency response = restOperations.getForObject(urlCurrency, Currency.class, sourceCurrency, destinationCurrency);
		int i=0;
		
		//Getting the currency rate out the string
		String fullRateStringObject =  (String) response.getRates().toString();
		System.out.println(fullRateStringObject);
		
		//Extracting the double numbers
		Matcher m = Pattern.compile("\\d+\\.?\\d*").matcher(fullRateStringObject);
		
		String[] ratePair = new String[2];
		while(m.find()){			
			//Population the array
			ratePair[i] = m.group();			
			i++;
		} 
			currencyRateFrom = new BigDecimal(ratePair[0].toString());
			MathContext mc = new MathContext(4); // 4 precision
		
			if(!(ratePair[1] == null)){ 				
				currencyRateTo = new BigDecimal(ratePair[1].toString());				
				multiplicationResult = amount.multiply(currencyRateTo, mc);
				FinalResult = multiplicationResult.divide(currencyRateFrom, 4);								
				return FinalResult;			
		}
	 
		FinalResult = amount.multiply(currencyRateFrom, mc);
		return FinalResult;
			
	}


	@Override
	public String toWords(BigDecimal number) {
		String response = restOperations.getForObject(urlNumbers, String.class, number);
		String numberToWord =  response;
		return numberToWord;
	}
	
	
}
