package com.creditshelf.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.creditshelf.entity.Company;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.util.Strings;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryTest {
	@Autowired
    private TestEntityManager entityManager;
 

    @Autowired 
    private CompanyRepository companyRepository;
    
    /// needed Objects
    private final String companyName="Company A";
   
    @Test
    public void whenFindByName_EmptyName_thenReturnNull() {
    	assert(!companyRepository.findByName(Strings.EMPTY).isPresent());
    }
    
    @Test
    public void whenFindByName_NullName_thenReturnNull() {    	
    	assert(!companyRepository.findByName(null).isPresent());
    }
    @Test
    public void whenFindByName_thenReturnCompany() {
    	Company company = new Company();
    	company.setName(companyName);
    	company = companyRepository.save(company);    	
    	   	
        entityManager.persist(company);
        entityManager.flush();
    
    	assert(companyRepository.findByName(companyName).isPresent());
    	assertThat(companyRepository.findByName(companyName).get().getName()).isEqualTo(companyName);
    }
    
}
