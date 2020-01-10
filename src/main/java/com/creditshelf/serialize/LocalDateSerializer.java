package com.creditshelf.serialize;

import com.creditshelf.exception.ValidationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;



public class LocalDateSerializer extends JsonSerializer<LocalDate> {

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
	        try {
	        	gen.writeString(value.format(ofPattern("M/dd/yy")));
	        } catch (DateTimeParseException | IOException ex) {
	            throw new ValidationException("Error while serializing date");
	        }
	}
		
	

    
}