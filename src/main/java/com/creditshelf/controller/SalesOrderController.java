package com.creditshelf.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditshelf.dto.RevenueProfitResponse;
import com.creditshelf.dto.SalesOrderResponse;
import com.creditshelf.service.order.SalesOrderService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/salesorder")
@Slf4j
public class SalesOrderController {
	
	@Autowired
	private SalesOrderService salesOrderService;
	
	 @GetMapping(value="/{companyName}", produces = "application/json")
	    public ResponseEntity<SalesOrderResponse> getOrdersListByCompany(@PathVariable("companyName") String companyName) {
		 log.info("Getting all the sales by  Company {}", companyName);
	        return new ResponseEntity<SalesOrderResponse>(salesOrderService.getSalesOrderByCompany(companyName),HttpStatus.OK);
	 }
	 
	 
	 @GetMapping(value="/{companyName}/revenues", produces = "application/json")
	    public ResponseEntity<RevenueProfitResponse> getCompanyRevenues(@PathVariable("companyName") String companyName) {
		 log.info("Getting the revenues report for Company {}", companyName);
	        return new ResponseEntity<RevenueProfitResponse>(salesOrderService.getRevenuesByCompany(companyName),HttpStatus.OK);
	 }
	 
	 @GetMapping(value="/{companyName}/netprofits", produces = "application/json")
	    public ResponseEntity<RevenueProfitResponse> getCompanyNetprofit(@PathVariable("companyName") String companyName) {
	       log.info("Getting the Sales Net Profit for Company {}", companyName);
	        return new ResponseEntity<RevenueProfitResponse>(salesOrderService.getNetProfitByCompany(companyName),HttpStatus.OK);
	 }

}
