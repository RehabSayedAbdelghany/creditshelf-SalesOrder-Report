package com.creditshelf.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.creditshelf.entity.Company;
import com.creditshelf.entity.Product;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired 
    private CompanyRepository companyRepository;
    
   
    private Company company,company2;
    private Product product;
    
    @Before
    public void before() {
    	company =  new Company();
    	company.setName("Company A");
    	
    	company2 =  new Company();
    	company.setName("Company B");
    	
    	product = new Product();
    	product.setName("Product 1");
    	product.setCompany(company);
    	product.setCurrency("EUR");
    	product.setProductId(1l);
    	product.setPrice(BigDecimal.TEN);
    	
    }
    @Test
    public void whenFindByCompanyAndProductId_thenReturnProduct() {
    	company = companyRepository.save(company);
    	
        entityManager.persist(product);
        entityManager.flush();
    
        Optional<Product> found = productRepository.findByCompanyAndProductId(company, 1l);
    
        assert(found.isPresent());
        assertThat(found.get().toString()).isEqualTo(product.toString());
    }
    
    @Test
    public void whenFindByCompanyAndProductId_WithIncorrectCompany() {    
    	company = companyRepository.save(company);    	
    	company2 = companyRepository.save(company2);
    	   	
        entityManager.persist(product);
        entityManager.flush();
    
        Optional<Product> found = productRepository.findByCompanyAndProductId(company2, 1l);
        
        assert(!found.isPresent());
    }
    
    @Test
    public void whenFindByCompanyAndProductId_WithNullCompany() {
    	
    
        Optional<Product> found = productRepository.findByCompanyAndProductId(null, 1l);
        
        assert(!found.isPresent());
    }
    
    @Test
    public void whenFindByCompanyAndProductId_WithIncorrectProductId() {
    	company = companyRepository.save(company);
      	
        entityManager.persist(product);
        entityManager.flush();
    
        Optional<Product> found = productRepository.findByCompanyAndProductId(company, 3l);
        
        assert(!found.isPresent());
    }
}
