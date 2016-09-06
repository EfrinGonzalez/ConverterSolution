package com.deltek.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestConfig {

	@Bean
	public RestOperations createTestTemplate(final ClientHttpRequestFactory ClientHttpRequestFactory ){
		return new RestTemplate(ClientHttpRequestFactory);
		
	}
	
	@Bean
	public ClientHttpRequestFactory createClientHTTPRequestFactory(@Value("${connect.timeout}") int connectTimeout, 
																   @Value("${read.timeout}") int readTimeout){
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
	//	HttpComponentsClientHttpRequestFactory.setReadTimeout(readTimeout);
	//	HttpComponentsClientHttpRequestFactory.setConnectTimeout(connectTimeout);
		return httpComponentsClientHttpRequestFactory;
	}
}
