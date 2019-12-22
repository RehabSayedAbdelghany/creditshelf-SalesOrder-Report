package com.creditshelf.service.product.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditshelf.entity.Company;
import com.creditshelf.entity.Product;
import com.creditshelf.parser.model.ProductCSVData;
import com.creditshelf.repository.ProductRepository;
import com.creditshelf.service.company.CompanyService;
import com.creditshelf.service.product.ProductService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private CompanyService companyService;
	
	@Autowired ProductRepository productRepository;

	@Transactional
	@Override
	public List<Product> saveProducts(List<ProductCSVData> productsData, String comapnyName) {
		log.info("Save Product from CSV files");
		Company company = companyService.getCompanyOrCreate(comapnyName);		
		
		List<Product> products = productRepository.saveAll(mapFromCSVDataToEntityModel(productsData,company));
		return products;
		
	}
	
	private List<Product> mapFromCSVDataToEntityModel(List<ProductCSVData> productsData,Company company){
		List<Product>  products = new ArrayList<Product>();
		for(ProductCSVData dat:productsData) {
			products.add(mapFromDatatoEntity(dat,company));
		}
		return products;
	}

	@Override
	public Optional<Product>   findByCompanyAndProductId(Company company,Long productId) {
		return productRepository.findByCompanyAndProductId(company, productId);
		
	}
	
	private Product mapFromDatatoEntity(ProductCSVData data,Company company) {
		Product product  =  new Product();
		product.setCompany(company);
		product.setProductId(data.getProductId());
		product.setPrice(data.getPrice());
		product.setCurrency(data.getCurrency());
		product.setName(data.getName());
		return product;
	}
	

}
