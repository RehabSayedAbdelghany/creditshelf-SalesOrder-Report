package com.creditshelf.dto;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class SalesOrderDetailsDTO {
	//needed for feature 2
	 private String productName;
	 private Long quantity;
	 private BigDecimal salePrice;


}
