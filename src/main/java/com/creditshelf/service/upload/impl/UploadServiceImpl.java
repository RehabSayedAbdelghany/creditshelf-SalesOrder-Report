package com.creditshelf.service.upload.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.creditshelf.exception.ValidationException;
import com.creditshelf.parser.Parser;
import com.creditshelf.parser.ParserFactory;
import com.creditshelf.parser.model.ProductCSVData;
import com.creditshelf.parser.model.SaleCSVData;
import com.creditshelf.service.order.SalesOrderService;
import com.creditshelf.service.product.ProductService;
import com.creditshelf.service.upload.UploadService;

import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService{
	@Autowired
	private ParserFactory parserFactory;
	
	@Autowired
	private ProductService productService;
	
	@Autowired SalesOrderService orderService;
	
	
	
	@Override
	public int  uploadProducts(MultipartFile file) throws Exception {

		List<ProductCSVData> data = readFile(ProductCSVData.class, file);
		String fileName=file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.'));
		return productService.saveProducts(data, fileName).size();
		
	}
	@Override
	public int uploadSales(MultipartFile file) throws Exception {

		List<SaleCSVData> data = readFile(SaleCSVData.class, file);
		return orderService.saveSales(data);
	
	}
	private Optional<String> getExtension(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
	
	private <T> List<T>  readFile(Class<T> clazz,MultipartFile file) throws IOException{
		Optional<String> fileType = getExtension(file.getOriginalFilename());
		if(!"csv".equalsIgnoreCase(fileType.get())){
			throw new ValidationException("File %s is not valid\"");
		}
		Parser parser =  parserFactory.getParser(fileType.get());
		return parser.read(clazz, file.getInputStream());
		
	}
	
}
