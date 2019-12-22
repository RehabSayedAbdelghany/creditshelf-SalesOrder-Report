package com.creditshelf.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.creditshelf.dto.RevenueProfitDTO;
import com.creditshelf.dto.RevenueProfitResponse;
import com.creditshelf.dto.SalesOrderDTO;
import com.creditshelf.dto.SalesOrderDetailsDTO;
import com.creditshelf.dto.SalesOrderResponse;
import com.creditshelf.service.order.SalesOrderService;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SalesOrderController.class)
public class SalesOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private  String companyName="Company 1";
    RevenueProfitResponse revenueProfit;

    @MockBean
    SalesOrderService saleOrderService; 
    

    private SalesOrderResponse response;
    
 
    

    @Before
    public void before() throws Exception {
  	
    	List<SalesOrderDetailsDTO> details = Arrays.asList(new SalesOrderDetailsDTO("Product 1",Long.valueOf(10),BigDecimal.valueOf(400)),
    			new SalesOrderDetailsDTO("Product 2",Long.valueOf(4),BigDecimal.valueOf(200)));
    	
    	List<SalesOrderDetailsDTO> details2 = Arrays.asList(new SalesOrderDetailsDTO("Product 3",Long.valueOf(7),BigDecimal.valueOf(567)),
		new SalesOrderDetailsDTO("Product 4",Long.valueOf(3),BigDecimal.valueOf(175)));
    	
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
    	
       	List<SalesOrderDTO> salesOrders = Arrays.asList(new SalesOrderDTO(Long.valueOf(1),LocalDate.parse("10/05/2019", formatter),BigDecimal.valueOf(500),details),
       			new SalesOrderDTO(Long.valueOf(2),LocalDate.parse("11/25/2019", formatter),BigDecimal.valueOf(1500),details2));
       	response = new SalesOrderResponse();
       	response.setResponse(salesOrders);
       	
       	
       	List<RevenueProfitDTO> revenuesProfits = Arrays.asList(new RevenueProfitDTO(companyName,1,BigDecimal.valueOf(20)),new RevenueProfitDTO(companyName,2,BigDecimal.valueOf(50)));
       	revenueProfit =  new RevenueProfitResponse();
       	revenueProfit.setResponse(revenuesProfits);
       	
    	
        
    }

    @Test
    public void getOrdersListByCompany_NotValidName () throws Exception {
        given(saleOrderService.getSalesOrderByCompany(companyName)).willReturn(new SalesOrderResponse());

        mockMvc.perform(get("/salesorder/{companyName}", companyName)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.response").value(IsNull.nullValue()));
                
    }

    @Test
    public void getOrdersListByCompany() throws Exception {
    	companyName="test";
        given(saleOrderService.getSalesOrderByCompany(companyName)).willReturn(response);

        mockMvc.perform(get("/salesorder/{companyName}", companyName)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.response[0].orderNumber", is(1)))
                .andExpect(jsonPath("$.response[0].total", is(500)))
                .andExpect(jsonPath("$.response[0].orderDetails[0].productName", is(response.getResponse().get(0).getOrderDetails().get(0).getProductName())))
                .andExpect(jsonPath("$.response[0].orderDetails[0].salePrice", is(400)))
                .andExpect(jsonPath("$.response[0].orderDetails[0].quantity", is(10)))
     
                .andExpect(jsonPath("$.response[0].orderDetails[1].productName", is(response.getResponse().get(0).getOrderDetails().get(1).getProductName())))
                .andExpect(jsonPath("$.response[0].orderDetails[1].salePrice", is(200)))
                .andExpect(jsonPath("$.response[0].orderDetails[1].quantity", is(4)))
                
                
                
                .andExpect(jsonPath("$.response[1].orderNumber", is(2)))
                .andExpect(jsonPath("$.response[1].total", is(1500)))
               // .andExpect(jsonPath("$.response[1].orderDate", is("11/25/2019")))
                .andExpect(jsonPath("$.response[1].orderDetails[0].productName", is(response.getResponse().get(1).getOrderDetails().get(0).getProductName())))
                .andExpect(jsonPath("$.response[1].orderDetails[0].salePrice", is(567)))
                .andExpect(jsonPath("$.response[1].orderDetails[0].quantity", is(7)))
     
                .andExpect(jsonPath("$.response[1].orderDetails[1].productName", is(response.getResponse().get(1).getOrderDetails().get(1).getProductName())))
                .andExpect(jsonPath("$.response[1].orderDetails[1].salePrice", is(175)))
                .andExpect(jsonPath("$.response[1].orderDetails[1].quantity", is(3)));
                
    }
    
    
    @Test
    public void testGetCompanyRevenues_InvalidName() throws Exception {
        given(saleOrderService.getRevenuesByCompany(companyName)).willReturn(new RevenueProfitResponse());

        mockMvc.perform(get("/salesorder/{companyName}/revenues", companyName)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value(IsNull.nullValue()));
    }

    @Test
    public void testGetCompanyRevenues() throws Exception {
        given(saleOrderService.getRevenuesByCompany(companyName)).willReturn(revenueProfit);

        mockMvc.perform(get("/salesorder/{companyName}/revenues", companyName)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response[0].month", is(1)))
                .andExpect(jsonPath("$.response[0].company",is(companyName)))
                .andExpect(jsonPath("$.response[0].total", is(20)))
                
                .andExpect(jsonPath("$.response[1].month", is(2)))
                .andExpect(jsonPath("$.response[1].company",is(companyName)))
                .andExpect(jsonPath("$.response[1].total", is(50)));

    }
    
    
    
    @Test
    public void testGetNetProfitByCompany_InvalidName() throws Exception {
        given(saleOrderService.getNetProfitByCompany(companyName)).willReturn(new RevenueProfitResponse());

        mockMvc.perform(get("/salesorder/{companyName}/netprofits", companyName)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value(IsNull.nullValue()));
    }

    @Test
    public void testGetNetProfitByCompany() throws Exception {
        given(saleOrderService.getNetProfitByCompany(companyName)).willReturn(revenueProfit);

        mockMvc.perform(get("/salesorder/{companyName}/netprofits", companyName)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response[0].month", is(1)))
                .andExpect(jsonPath("$.response[0].company",is(companyName)))
                .andExpect(jsonPath("$.response[0].total", is(20)))
                
                .andExpect(jsonPath("$.response[1].month", is(2)))
                .andExpect(jsonPath("$.response[1].company",is(companyName)))
                .andExpect(jsonPath("$.response[1].total", is(50)));
    }
}
