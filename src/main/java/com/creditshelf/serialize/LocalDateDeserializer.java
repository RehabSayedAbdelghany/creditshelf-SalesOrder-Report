package com.creditshelf.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;



public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		if(p.getText().isEmpty())
			return null;
		Locale bLocale = new Locale("en", "US");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy",bLocale);
		return LocalDate.parse(p.getText(), formatter);
		
	}


	
	

    
}