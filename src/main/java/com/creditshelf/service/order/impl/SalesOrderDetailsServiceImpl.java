package com.creditshelf.service.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditshelf.entity.SalesOrderDetails;
import com.creditshelf.repository.SalesOrderDetailsRepository;
import com.creditshelf.service.order.SalesOrderDetailsService;

@Service
public class SalesOrderDetailsServiceImpl implements SalesOrderDetailsService{

	@Autowired
	private SalesOrderDetailsRepository salesOrderDetailsRepository;
	
	@Override
	public List<SalesOrderDetails> saveAll(List<SalesOrderDetails> details) {
		return salesOrderDetailsRepository.saveAll(details);
	}

}
