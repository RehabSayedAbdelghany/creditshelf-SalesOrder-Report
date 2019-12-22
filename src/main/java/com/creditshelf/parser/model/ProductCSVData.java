package com.creditshelf.parser.model;

import java.math.BigDecimal;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.creditshelf.serialize.FlexibleBigDecimalDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) 
public class ProductCSVData {

	@JsonProperty("id")
	@JsonAlias({"Prod. Id","Id","Product Id"})
    private Long productId;

	@JsonProperty("name")
    @JsonAlias({"Description","Product","Name"})
    private String name;

	@JsonProperty("price")
    @JsonAlias({"Purchase price", "Assembly cost", "Build cost"})
	@JsonDeserialize(using = FlexibleBigDecimalDeserializer.class)
    private BigDecimal price;
    
	@JsonProperty("currency")
	@JsonAlias({"Currency"})
    private String currency;
}
