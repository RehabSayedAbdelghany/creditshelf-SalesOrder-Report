package com.creditshelf.parser.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.creditshelf.serialize.LocalDateDeserializer;
import com.creditshelf.serialize.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaleCSVData {
	@JsonProperty("Company") 
	private String company;
	 
	@JsonProperty("Order date") 
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate orderDate;
	 
	@JsonProperty("Order number") 
	private Long orderNumber;
	
	@JsonProperty("Product Id") 
	private Long productId;
	
	@JsonProperty("Quantity") 
	private Long quantity;
	
	@JsonProperty("Sale price")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal salesPrice;
	
	@JsonProperty("Currency")
	private String currency;

	

}
