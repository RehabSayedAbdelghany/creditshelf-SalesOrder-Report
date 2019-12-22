package com.creditshelf.service.order;

import java.util.List;

import com.creditshelf.dto.RevenueProfitResponse;
import com.creditshelf.dto.SalesOrderResponse;
import com.creditshelf.parser.model.SaleCSVData;

public interface SalesOrderService {
	int saveSales(List<SaleCSVData> salesData);
	SalesOrderResponse getSalesOrderByCompany(String companyName);
	RevenueProfitResponse getRevenuesByCompany(String companyName);
	RevenueProfitResponse getNetProfitByCompany(String companyName);
}
