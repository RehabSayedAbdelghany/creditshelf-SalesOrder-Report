package com.creditshelf.parser.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.creditshelf.parser.Parser;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Service
public class CSVParser implements Parser {
	private final CsvMapper mapper = new CsvMapper();

	@Override
	public <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
		 CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
	     ObjectReader reader = mapper.readerFor(clazz).with(schema);
	     return reader.<T>readValues(stream).readAll();
	}

}
