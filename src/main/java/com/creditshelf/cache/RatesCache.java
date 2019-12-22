package com.creditshelf.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import com.creditshelf.client.ExchangeRateAPI;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
@Component
public class RatesCache {

	@Value("${creditshelf.cache.expiry}")
	private int CACHE_DURATION;
	
	@Autowired
	private ExchangeRateAPI exchangeRateAPI;

	private Cache<String, BigDecimal> cache;

	@PostConstruct
	public void init() {
		if (cache == null) {					
			cache = CacheBuilder.newBuilder().expireAfterWrite(CACHE_DURATION, TimeUnit.MINUTES).build();
			cache.putAll(exchangeRateAPI.getExchangeRates().getRates());
		}
		
	}
	public BigDecimal getCachedRate(String code) {
		return cache.getIfPresent(code);
	}
}
