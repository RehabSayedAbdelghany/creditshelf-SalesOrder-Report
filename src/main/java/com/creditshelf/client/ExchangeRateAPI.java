package com.creditshelf.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.creditshelf.dto.ExchangeRates;
import com.creditshelf.exception.CreditShelfException;


@Component
public class ExchangeRateAPI {
	@Value("${creditshelf.currency.api.base.uri}")
	private String url;


    
    public ExchangeRates getExchangeRates() throws CreditShelfException {
        try {
        	RestTemplate restTemplate = new RestTemplate();
        	return  restTemplate.getForObject(url, ExchangeRates.class);
           
        } catch (Exception ex) {
            throw new CreditShelfException("Error While Calling exchange rate API"+ex.getCause());
        }
    }
}
