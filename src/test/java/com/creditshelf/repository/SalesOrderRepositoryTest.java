package com.creditshelf.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.creditshelf.entity.Company;
import com.creditshelf.entity.Product;
import com.creditshelf.entity.RevenueProfit;
import com.creditshelf.entity.SalesOrder;
import com.creditshelf.entity.SalesOrderDetails;


@RunWith(SpringRunner.class)
@DataJpaTest
public class SalesOrderRepositoryTest {

	@Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private SalesOrderRepository salesOrderRepository;
   
   

    private Company c1;
    private Product p1,p2;
    private SalesOrder s1,s2;
    private SalesOrderDetails sd1,sd2;
    @Before
    public void before() throws Exception {
    	c1 = new Company(null,"Copmany 1");

    	p1 =new Product(null,1l,"Product 1 for company 1",BigDecimal.valueOf(18.15),"GBP",c1);
    	p2 =new Product(null,2l,"Product 2 for company 1",BigDecimal.valueOf(17.50),"GBP",c1);
    	
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
    	
    	s1 = new SalesOrder (null,1l,LocalDate.parse("10/05/2019", formatter),c1,null);
    	s2 = new SalesOrder (null,3l,LocalDate.parse("11/11/2019", formatter),c1,null);

    	
    	sd1=new SalesOrderDetails(null,2l,BigDecimal.valueOf(29.99),"EUR",p2,s1);
    	sd2=new SalesOrderDetails(null,2l,BigDecimal.valueOf(32),"USD",p1,s2);
    	
    	s2.setSalesOrderDetails(new ArrayList<SalesOrderDetails>());
    	s1.setSalesOrderDetails(new ArrayList<SalesOrderDetails>());
    	
    	
    	s1.getSalesOrderDetails().add(sd1);
    	s2.getSalesOrderDetails().add(sd2);

    }

    @Test
    public void findAllSalesByCompanyName_NotExistCompany_ReturnEmptyList() {
        	
    	List<SalesOrder> salesOrders=salesOrderRepository.findByCompany_name("testing");
    	 assertTrue(salesOrders.isEmpty());
    }
    @Test
    public void findAllSalesByCompanyName_NullCompany_ReturnEmptyList() {
        	
    	List<SalesOrder> salesOrders=salesOrderRepository.findByCompany_name(null);
    	 assertTrue(salesOrders.isEmpty());
    }
    
//    @Test
//    public void findAllSalesByCompanyName_ExistCompany_ReturnList() {
//      	c1 =testEntityManager.persistAndFlush(c1);
//    	p1.setCompany(c1);
//    	p1=testEntityManager.persistAndFlush(p1);
//    	p2.setCompany(c1);
//    	p2=testEntityManager.persistAndFlush(p2);
//    	
//    	s2.setCompany(c1);
//    	s1.setCompany(c1);
//    	
//    	s1=testEntityManager.persistAndFlush(s1);
//    	s2=testEntityManager.persistAndFlush(s2);
//    	sd1.setProduct(p2);
//    	sd2.setProduct(p1);
//    	sd1=testEntityManager.persistAndFlush(sd1);
//    	sd2=testEntityManager.persistAndFlush(sd2);
//    	
//    	s1.getSalesOrderDetails().isEmpty();
//    	s1.getSalesOrderDetails().add(sd1);
//    	
//    	
//    	s2.getSalesOrderDetails().isEmpty();
//    	s2.getSalesOrderDetails().add(sd2);
//    	
//    	s1=testEntityManager.persistAndFlush(s1);
//    	s2=testEntityManager.persistAndFlush(s2);
//        	
//    	List<SalesOrder> salesOrders=salesOrderRepository.findByCompany_name("Company 1");
//    	 assertThat(salesOrders.size()).isEqualTo(2);
//    	 assertThat(salesOrders.get(0).getSalesOrderDetails().size()).isEqualTo(1);
//    	 assertThat(salesOrders.get(0).getSalesOrderDetails().get(0).getProduct().getCurrency()).isEqualTo("GBP");
//    	 assertThat(salesOrders.get(0).getCompany().getName()).isEqualTo("Company 1");
//    	 assertThat(salesOrders.get(0).getCurrencyCode()).isEqualTo("EUR");
//    	 assert(salesOrders.get(0).getTotal().equals(BigDecimal.valueOf(59.98)));
//    	 
//    	 assertThat(salesOrders.get(1).getSalesOrderDetails().size()).isEqualTo(1);
//    	 assertThat(salesOrders.get(1).getSalesOrderDetails().get(0).getProduct().getCurrency()).isEqualTo("GBP");
//    	 assertThat(salesOrders.get(1).getCompany().getName()).isEqualTo("Company 1");
//    	 assertThat(salesOrders.get(1).getCurrencyCode()).isEqualTo("USD");
//    	 
//    	 
//    }
    
    @Test
    public void findRevenuesByCompany_EmptyCompany_ReturnEmptyList() {
        	
    	List<RevenueProfit> salesOrders=salesOrderRepository.findRevenuesByCompany(Strings.EMPTY);
    	 assertTrue(salesOrders.isEmpty());
    }
    @Test
    public void findRevenuesByCompany_NullCompany_ReturnEmptyList() {
        	
    	List<RevenueProfit> salesOrders=salesOrderRepository.findRevenuesByCompany(null);
    	 assertTrue(salesOrders.isEmpty());
    }
    
//    @Test
//    public void findRevenuesByCompany_ExistCompany_ReturnEmptyList() {
//      	c1 =testEntityManager.persistAndFlush(c1);
//    	p1.setCompany(c1);
//    	p1=testEntityManager.persistAndFlush(p1);
//    	p2.setCompany(c1);
//    	p2=testEntityManager.persistAndFlush(p2);
//    	
//    	s2.setCompany(c1);
//    	s1.setCompany(c1);
//    	
//    	s1=testEntityManager.persistAndFlush(s1);
//    	s2=testEntityManager.persistAndFlush(s2);
//    	sd1.setProduct(p2);
//    	sd2.setProduct(p1);
//    	sd1=testEntityManager.persistAndFlush(sd1);
//    	sd2=testEntityManager.persistAndFlush(sd2);
//    	
//    	s1.getSalesOrderDetails().isEmpty();
//    	s1.getSalesOrderDetails().add(sd1);
//    	
//    	
//    	s2.getSalesOrderDetails().isEmpty();
//    	s2.getSalesOrderDetails().add(sd2);
//    	
//    	s1=testEntityManager.persistAndFlush(s1);
//    	s2=testEntityManager.persistAndFlush(s2);
//    	
//    	List<RevenueProfit> revenues=salesOrderRepository.findRevenuesByCompany("Company 1");
//    	assertThat(revenues.size()).isEqualTo(2);
//    	assertThat(revenues.get(0).getCompany()).isEqualTo("Company 1");
//    	assertThat(revenues.get(0).getCurrency()).isEqualTo("EUR");
//    	assertThat(revenues.get(0).getProductCurrency()).isEqualTo("GBP");
//    	assertThat(revenues.get(0).getMonth()).isEqualTo(1);
//    	assert(revenues.get(0).getTotal().equals(BigDecimal.valueOf(59.98)));
//    	assertThat(revenues.get(0).getProductPrice().intValue()).isEqualTo(0);
//    	
//    	assertThat(revenues.get(1).getCompany()).isEqualTo("Company 1");
//    	assertThat(revenues.get(1).getCurrency()).isEqualTo("USD");
//    	assertThat(revenues.get(1).getProductCurrency()).isEqualTo("GBP");
//    	assertThat(revenues.get(1).getMonth()).isEqualTo(6);
//    	assertThat(revenues.get(1).getProductPrice().intValue()).isEqualTo(0);
//    	 
//    	 
//    }
    
    @Test
    public void findNetProfitByCompany_EmptyCompany_ReturnEmptyList() {
        	
    	List<RevenueProfit> salesOrders=salesOrderRepository.findNetProfitByCompany(Strings.EMPTY);
    	 assertTrue(salesOrders.isEmpty());
    }
    @Test
    public void findNetProfitByCompany_NullCompany_ReturnEmptyList() {
        	
    	List<RevenueProfit> salesOrders=salesOrderRepository.findNetProfitByCompany(null);
    	 assert(salesOrders.isEmpty());
    }
    
//    @Test
//    public void findNetProfitByCompany_ExistCompany_ReturnEmptyList() {
//    	c1 =testEntityManager.persistAndFlush(c1);
//    	p1.setCompany(c1);
//    	p1=testEntityManager.persistAndFlush(p1);
//    	p2.setCompany(c1);
//    	p2=testEntityManager.persistAndFlush(p2);
//    	
//    	s2.setCompany(c1);
//    	s1.setCompany(c1);
//    	
//    	s1=testEntityManager.persistAndFlush(s1);
//    	s2=testEntityManager.persistAndFlush(s2);
//    	sd1.setProduct(p2);
//    	sd2.setProduct(p1);
//    	sd1=testEntityManager.persistAndFlush(sd1);
//    	sd2=testEntityManager.persistAndFlush(sd2);
//    	
//    	s1.getSalesOrderDetails().isEmpty();
//    	s1.getSalesOrderDetails().add(sd1);
//    	
//    	
//    	s2.getSalesOrderDetails().isEmpty();
//    	s2.getSalesOrderDetails().add(sd2);
//    	
//    	s1=testEntityManager.persistAndFlush(s1);
//    	s2=testEntityManager.persistAndFlush(s2);
//    	List<RevenueProfit> revenues=salesOrderRepository.findNetProfitByCompany("Company 1");
//    	assertThat(revenues.size()).isEqualTo(2);
//    	assertThat(revenues.get(0).getCompany()).isEqualTo("Company 1");
//    	assertThat(revenues.get(0).getCurrency()).isEqualTo("EUR");
//    	assertThat(revenues.get(0).getProductCurrency()).isEqualTo("GBP");
//    	assertThat(revenues.get(0).getMonth()).isEqualTo(1);
//    	assert(revenues.get(0).getTotal().equals(BigDecimal.valueOf(59.98)));
//    	assertThat(revenues.get(0).getProductPrice().doubleValue()).isEqualTo(35d);
//    	
//    	
//    	assertThat(revenues.get(1).getCompany()).isEqualTo("Company 1");
//    	assertThat(revenues.get(1).getCurrency()).isEqualTo("USD");
//    	assertThat(revenues.get(1).getProductCurrency()).isEqualTo("GBP");
//    	assertThat(revenues.get(1).getMonth()).isEqualTo(6);
//    	assertThat(revenues.get(1).getTotal().doubleValue()).isEqualTo(96d);
//    	assert(revenues.get(1).getProductPrice().equals(BigDecimal.valueOf(54.45)));
//
//    	 
//    }
}
