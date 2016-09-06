package com.deltek.app;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.deltek.app.converter.Converter;
import com.deltek.app.model.Currency;




@SpringBootApplication
public class App  implements CommandLineRunner{

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	private Converter converter;
	
	@Override
	public void run(String... args) throws Exception {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigDecimal amount = null;
	    String fromCurrency = null;
	    String toCurrency = null;
	    String line = "";
	    String amountString = null; 
	    
	    System.out.println("Autor: Efrin González");
	    System.out.println("-----------------------------------");
        System.out.println("Welcome to the currency conversion app. By convention the base currency is Euro.\n");
        System.out.println("Choose currencies from the list: \n"+
        			       "[DKK,GBP,EUR,USD,AUD,JPY,\n"+
        				   " BGN,BRL,CAD,CHF,CNY,CZK,\n"+
        				   " HKD,HRK,HUF,IDR,ILS,INR,\n"+
        				   " KRW,MXN,MYR,NOK,NZD,PHP,\n"+
        				   " PLN,RON,RUB,SEK,SGD,THB,\n"+
        				   " TRY,ZAR]");
    	
        System.out.println("-----------------------------------");
        System.out.println("Press Enter to start or type 'exit'");
        try{
        	
        	/*Notice that this code can be improved with a whole set of different validations on the inputs.
        	*/
        	while ((line = br.readLine()) != null && !line.equals("exit") ){                     	
        		
        		System.out.println("Insert amount to convert\n");
        		while((amountString = br.readLine().toString())  != null &&
        				!(amountString.length() > 0)){
  	        		System.out.println("Amount is empty. Try again!");
  	        	}
        		amount = new BigDecimal(amountString.toString());
	        	
	        	System.out.println("Insert from which currency you want to convert from (Ex: DKK)\n");	        	
	        	while((fromCurrency = br.readLine().toUpperCase()) != null &&
	        		  !(fromCurrency.length() == 3)){
	        		System.out.println("Currency from is not in valid format. Try again!");
	        		
	        	}
	        		        	
	        	System.out.println("Insert to which currency you want to convert to (Ex: USD)\n");
	        	while((toCurrency = br.readLine().toUpperCase()) != null &&
		        		  !(toCurrency.length() == 3)){
	        		System.out.println("Currency to is not in a valid format. Try again!");
		        		
		        	}
	        	
		        System.out.println("-----------------------------------");
		        BigDecimal changedCurrency = converter.convert(amount, fromCurrency, toCurrency);
				
				String numberToWord = converter.toWords(changedCurrency);				
				System.out.println("You have changed "+ amount + " from " + fromCurrency + " to "+ toCurrency+
        				"-- The new amount is (rounded): "+changedCurrency.toString() + " '"+ numberToWord+"' "+ toCurrency);
				
				System.out.println("Calculate other amount: press Enter again");
				System.out.println("Finish: type 'exit'");
        	}       	
        		
        		br.close();
        		System.out.println("Bye bye...!");
        		
        }catch(IOException e){
        	e.printStackTrace();
        }			
		
	}

	
}
