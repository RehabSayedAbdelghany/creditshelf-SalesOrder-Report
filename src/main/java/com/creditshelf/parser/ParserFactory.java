package com.creditshelf.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creditshelf.parser.impl.CSVParser;


@Component
public class ParserFactory {
	@Autowired
	private CSVParser csvParser;
	public Parser getParser(String fileType){
	      if(fileType == null){
	         return null;
	      }		
	      if(fileType.equalsIgnoreCase("csv"))
	      {
	         return csvParser;
	      }
	     
	      
	      return null;
	   }
}
