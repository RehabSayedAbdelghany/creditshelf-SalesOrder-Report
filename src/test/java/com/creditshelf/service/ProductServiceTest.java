package com.creditshelf.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.creditshelf.entity.Company;
import com.creditshelf.entity.Product;
import com.creditshelf.parser.model.ProductCSVData;
import com.creditshelf.repository.CompanyRepository;
import com.creditshelf.repository.ProductRepository;
import com.creditshelf.service.company.CompanyService;
import com.creditshelf.service.company.impl.CompanyServiceImpl;
import com.creditshelf.service.product.ProductService;
import com.creditshelf.service.product.impl.ProductServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    
    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @InjectMocks
    CompanyServiceImpl companyService;
    
    private Company company,company2;
    private Product product,product2;
    List<ProductCSVData> csvList;
    List<Product> products;
    
    @Before
    public void before() {
    	company =  new Company();
    	company.setId(1l);
    	company.setName("Company A");
    	company2 =  new Company();
    	company.setName("Company B");
    	
    	product = new Product();
    	product.setName("Product 1");
    	product.setCompany(company);
    	product.setCurrency("EUR");
    	product.setProductId(1l);
    	product.setPrice(BigDecimal.TEN);
    	
    	product2 = new Product();
    	product2.setName("Product 2");
    	product2.setCompany(company);
    	product2.setCurrency("EUR");
    	product2.setProductId(1l);
    	product2.setPrice(BigDecimal.ONE);
    	products = new ArrayList<Product>();
    	products.add(product);
    	products.add(product2);
    	csvList = Arrays.asList(new ProductCSVData(1l,"Product 1",BigDecimal.TEN,"EUR"),new ProductCSVData(2l,"Product 2",BigDecimal.ONE,"EUR"));
    	
    }
    
    
    @Test
    public void testFindByCompanyAndProductId_thenReturnProduct() {
    	Optional<Product> optionalProduct =  Optional.of(product);
    	
    	 when(productRepository.findByCompanyAndProductId(company, 1l)).thenReturn(optionalProduct);
    	 
    	 Optional<Product> result = productService.findByCompanyAndProductId(company, 1l);
    	 assert(result.isPresent());
         assertThat(result.get().toString()).isEqualTo(optionalProduct.get().toString());
      }
    @Test
    public void testFindByCompanyAndProductId_NullCompany_thenReturnEmptyOptioanl() {    	
    	 when(productRepository.findByCompanyAndProductId(null, 1l)).thenReturn(Optional.empty());
    	 
    	 Optional<Product> result = productService.findByCompanyAndProductId(null, 1l);
    	 assert(!result.isPresent());
      }
    
    public void testFindByCompanyAndProductId_NonExistCompanyCompany_thenReturnEmptyOptioanl() {    	
   	 when(productRepository.findByCompanyAndProductId(company2, 1l)).thenReturn(Optional.empty());
   	 
   	 Optional<Product> result = productService.findByCompanyAndProductId(company2, 1l);
   	 assert(!result.isPresent());
     }
    
    public void testFindByCompanyAndProductId_NonExistProductId_thenReturnEmptyOptioanl() {    	
      	 when(productRepository.findByCompanyAndProductId(company, 22l)).thenReturn(Optional.empty());
      	 
      	 Optional<Product> result = productService.findByCompanyAndProductId(company, 22l);
      	 assert(!result.isPresent());
        }
       
    
//    
//    @Test
//    public void testsaveProducts() throws Exception {
//        when(productRepository.saveAll(products)).thenReturn(products);
//        Optional<Company> comOptioan = Optional.of(company);
//        when(companyService.getCompanyOrCreate("Company 1")).thenReturn(company);
//        List<Product> result = productService.saveProducts(csvList, "Company 1");
//
//        assertFalse(result.isEmpty());
//        assertEquals(2, result.size());
//        assertEquals(1, result.get(0).getProductId().intValue());
//        assertEquals("Company 1",  result.get(0).getCompany().getName());
//        assertEquals("Product 1", result.get(0).getName());
//        assertEquals(BigDecimal.TEN, result.get(0).getPrice());
//        assertEquals("EUR", result.get(0).getCurrency());
//    }
    
   
}
