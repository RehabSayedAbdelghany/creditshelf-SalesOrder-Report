package com.creditshelf.serialize;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class FlexibleBigDecimalDeserializer extends JsonDeserializer<BigDecimal> {


	@Override
	public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String floatString = p.getText();
        if (floatString.contains(",")) {
            floatString = floatString.replace(",", ".");
        }
        return new BigDecimal(floatString);
	}

}