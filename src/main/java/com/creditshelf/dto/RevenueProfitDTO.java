package com.creditshelf.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class RevenueProfitDTO {
	  	private int month;
	  	private String company;
	    private BigDecimal total;
	    
	   public RevenueProfitDTO(String company,int month,BigDecimal total) {
		   
		   this.company=company;
		   this.month=month;
		   this.total=total;
		   
	   }
}
