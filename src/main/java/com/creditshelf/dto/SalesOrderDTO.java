package com.creditshelf.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.creditshelf.serialize.LocalDateDeserializer;
import com.creditshelf.serialize.LocalDateSerializer;
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
public class SalesOrderDTO {
	//needed for feature 2
	private Long orderNumber;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate orderDate;
	
	private BigDecimal total;
	
	private List<SalesOrderDetailsDTO> orderDetails = new ArrayList<>();
	
	/////////
 
    

}
