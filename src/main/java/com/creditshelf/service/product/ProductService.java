package com.creditshelf.service.product;

import java.util.List;
import java.util.Optional;

import com.creditshelf.entity.Company;
import com.creditshelf.entity.Product;
import com.creditshelf.parser.model.ProductCSVData;

public interface ProductService {
	
	List<Product> saveProducts(List<ProductCSVData> productsData,String comapnyName);
	Optional<Product> findByCompanyAndProductId(Company company,Long productId);

}
