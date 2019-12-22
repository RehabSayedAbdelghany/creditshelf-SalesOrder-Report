package com.creditshelf.entity;

import java.math.BigDecimal;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RevenueProfit {
	private String company;
	private int month;
	private String currency;
	private BigDecimal total;
	private BigDecimal productPrice;
	private String productCurrency;
	

	public RevenueProfit(String company,int month,BigDecimal productPrice,BigDecimal total,String currency,String productCurrency) {
		this.company=company;
		this.month=month;
		this.currency = currency;
		this.total=total;
		this.productPrice=productPrice;
		this.productCurrency=productCurrency;
		
	}
	
}
