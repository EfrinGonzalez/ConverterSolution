package com.deltek.app.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.deltek.app.model.Currency;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTests {

	@Autowired
	private RestTemplate restTemplate;
	
	

	// This will hold the port number the server was started on
    @Value("${currency.service.url}")
    String url;
    
    String sourceCurrency;
    String destinationCurrency;
    
    @Before public void initialize() {
        System.out.println("Inside tests...!");
     }
    
    @Test
	public void contextLoads() {
		assertThat(restTemplate).isNotNull();
	}

	@Test
	public void requestIsNotEmpty(){
		sourceCurrency = "USD";
		destinationCurrency = "DKK";
		Currency request = restTemplate.getForObject(url, Currency.class, sourceCurrency, destinationCurrency);	
		assertThat(request).isNotNull();	
	}
	
	
}
