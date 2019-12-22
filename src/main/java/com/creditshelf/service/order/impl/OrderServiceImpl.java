package com.creditshelf.service.order.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditshelf.cache.RatesCache;
import com.creditshelf.dto.RevenueProfitDTO;
import com.creditshelf.dto.RevenueProfitResponse;
import com.creditshelf.dto.SalesOrderDTO;
import com.creditshelf.dto.SalesOrderDetailsDTO;
import com.creditshelf.dto.SalesOrderResponse;
import com.creditshelf.entity.Company;
import com.creditshelf.entity.RevenueProfit;
import com.creditshelf.entity.SalesOrder;
import com.creditshelf.entity.SalesOrderDetails;
import com.creditshelf.exception.CreditShelfException;
import com.creditshelf.parser.model.SaleCSVData;
import com.creditshelf.repository.SalesOrderRepository;
import com.creditshelf.service.company.CompanyService;
import com.creditshelf.service.order.SalesOrderDetailsService;
import com.creditshelf.service.order.SalesOrderService;
import com.creditshelf.service.product.ProductService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class OrderServiceImpl implements SalesOrderService{
	
	@Value("${creditshelf.currency.base}")
	private String baseCurrency;
	
	@Autowired
	private SalesOrderRepository orderRepository;
	
	@Autowired
	private SalesOrderDetailsService orderDetailsService;
	
	@Autowired 
	private CompanyService companyService;
	
	@Autowired 
	private ProductService productService;
	
	@Autowired
	private RatesCache ratesCache;
	
	@Transactional
	@Override
	public int saveSales(List<SaleCSVData> salesDataList) {
		log.info("Start Saving the sales records of the CSV to DB");
		List<SalesOrder> salesOrdersList = new ArrayList<>();
		List<SalesOrderDetails> salesOrderDetailsList = new ArrayList<>();
		SalesOrder salesOrder =  null;
		Company company  = null;
		for(SaleCSVData saleData:salesDataList) {
			
			if(saleData.getCompany().isEmpty()){
				salesOrderDetailsList.add(mapFromCSVDataToDetailsModel(saleData,salesOrder.getCompany()));
				
			}else {
				// new sales order
				if(!salesOrderDetailsList.isEmpty()) {
					salesOrderDetailsList=orderDetailsService.saveAll(salesOrderDetailsList);
					salesOrder.setSalesOrderDetails(salesOrderDetailsList);
					salesOrder=orderRepository.save(salesOrder);	
					salesOrdersList.add(salesOrder);
					salesOrderDetailsList=new ArrayList<SalesOrderDetails>();
					log.info("Save the order and move to the next one");
				}
				salesOrder = new SalesOrder();
				company =  companyService.findByName(saleData.getCompany()).orElseThrow(() -> new CreditShelfException("Company Not found Exception with name: " + saleData.getCompany()));
				salesOrder.setCompany(company);
				salesOrder.setOrderDate(saleData.getOrderDate());
				salesOrder.setOrderNumber(saleData.getOrderNumber());
				salesOrder=orderRepository.save(salesOrder);
				salesOrderDetailsList.add(mapFromCSVDataToDetailsModel(saleData,salesOrder.getCompany()));
				
				/// do the same for filling the order details
			}
			salesOrderDetailsList=orderDetailsService.saveAll(salesOrderDetailsList);
			salesOrder.setSalesOrderDetails(salesOrderDetailsList);
			salesOrder=orderRepository.save(salesOrder);	
			salesOrdersList.add(salesOrder);
			
		}	
		return salesOrdersList.size();
	}
	
	//format SalesOrderDetails item
	private SalesOrderDetails mapFromCSVDataToDetailsModel(SaleCSVData saleData,Company compnay) {
		
		SalesOrderDetails details =  new SalesOrderDetails();
		details.setCurrencyCode(saleData.getCurrency());
		details.setQuantity(saleData.getQuantity());
		details.setSalePrice(saleData.getSalesPrice());
		details.setProduct(productService.findByCompanyAndProductId(compnay, saleData.getProductId())
									.orElseThrow(() -> new CreditShelfException("Product Not found Exception with company and product id " + saleData.getCompany()+" & "+ saleData.getProductId())));
		return details;
	}

	@Override
	public SalesOrderResponse getSalesOrderByCompany(String companyName) {
		log.info("Start getSalesOrderByCompany");
		List<SalesOrder> salesOrder = orderRepository.findByCompany_name(companyName);
		log.info("salesOrder retrieved");
		SalesOrderResponse response = new SalesOrderResponse();
		if(salesOrder.isEmpty())
			return response;
		
		response.setResponse( mapFromEntityToDTO(salesOrder));
		return response;
	}
	
	private SalesOrderDTO getOrderDto(SalesOrder salesorder) {
		log.info("Inside getOrderDto to get the DTO of Order and handle the rates");
		SalesOrderDTO dto  = new SalesOrderDTO();
		dto.setOrderNumber(salesorder.getOrderNumber());
		dto.setOrderDate(salesorder.getOrderDate());
		if(baseCurrency.equalsIgnoreCase(salesorder.getCurrencyCode())) {
			dto.setTotal(salesorder.getTotal());
		}else {
			dto.setTotal(salesorder.getTotal().divide(ratesCache.getCachedRate(salesorder.getCurrencyCode()), RoundingMode.HALF_UP));
		}
		
		dto.setOrderDetails(mapFromDetailsEntityToDTO(salesorder.getSalesOrderDetails()));
		return dto;
	}
	private List<SalesOrderDTO> mapFromEntityToDTO(List<SalesOrder> salesOrderData){
		return salesOrderData.stream().map(this::getOrderDto).collect(Collectors.toList());
	}
	private SalesOrderDetailsDTO getOrderDetailsDto(SalesOrderDetails salesOrderDetails) {
		SalesOrderDetailsDTO dto  = new SalesOrderDetailsDTO();
		dto.setProductName(salesOrderDetails.getProduct().getName());
		dto.setQuantity(salesOrderDetails.getQuantity());
		dto.setSalePrice(salesOrderDetails.getSalePrice());
		return dto;
	}
	private List<SalesOrderDetailsDTO> mapFromDetailsEntityToDTO(List<SalesOrderDetails> salesOrderData){
		
		return salesOrderData.stream().map(this::getOrderDetailsDto).collect(Collectors.toList());
		
	}

	@Override
	public RevenueProfitResponse getRevenuesByCompany(String companyName) {
		log.info("Inside getRevenuesByCompany to revenue groupped by commpany currency month");
		List<RevenueProfit>  revenues=orderRepository.findRevenuesByCompany(companyName);
		log.info("data retrieved and going to make rate conversion and map to response");
		return getResponseModel(revenues);
	}

	private RevenueProfitDTO getRevenuesProfitDto(RevenueProfit revenue){
		RevenueProfitDTO dto = new RevenueProfitDTO();
		dto.setCompany(revenue.getCompany());
		dto.setMonth(revenue.getMonth());
		BigDecimal productPrice=BigDecimal.ZERO;
		if(baseCurrency.equalsIgnoreCase(revenue.getCurrency())) {
		
			dto.setTotal(revenue.getTotal());
		}else {
			dto.setTotal(revenue.getTotal().divide(ratesCache.getCachedRate(revenue.getCurrency()), RoundingMode.HALF_UP));
		}
		
		if(baseCurrency.equalsIgnoreCase(revenue.getProductCurrency()))
		{
			productPrice = revenue.getProductPrice();
		}else {
			productPrice = revenue.getProductPrice().divide(ratesCache.getCachedRate(revenue.getProductCurrency()), RoundingMode.HALF_UP);
		}
		dto.setTotal(dto.getTotal().subtract(productPrice));
		return dto;
		
	}

	@Override
	public RevenueProfitResponse getNetProfitByCompany(String companyName) {
		log.info("Inside getNetProfitByCompany to profit groupped by commpany currency month");
		List<RevenueProfit>  netProfit=orderRepository.findNetProfitByCompany(companyName);
		log.info("data retrieved and going to make rate conversion and map to response");
		return getResponseModel(netProfit);
	}
	
	private RevenueProfitResponse getResponseModel(List<RevenueProfit> revenues) {
		RevenueProfitResponse response = new RevenueProfitResponse();
		List<RevenueProfitDTO> dtos =revenues.stream().map(this::getRevenuesProfitDto).collect(Collectors.toList());
		dtos=dtos.stream()
			    .collect(Collectors.groupingBy(
			    		RevenueProfitDTO::getCompany,
			            Collectors.groupingBy(
			            	RevenueProfitDTO::getMonth,
			                Collectors.reducing(
			                    BigDecimal.ZERO,
			                    RevenueProfitDTO:: getTotal,
			                    BigDecimal::add))))
			        .entrySet()
			        .stream()
			        .flatMap(e1 -> e1.getValue()
			             .entrySet()
			             .stream()
			             .map(e2 -> new RevenueProfitDTO(e1.getKey(),e2.getKey() ,e2.getValue() )))
			        .collect(Collectors.toList());

		response.setResponse(dtos);
		return response;
	}
}
