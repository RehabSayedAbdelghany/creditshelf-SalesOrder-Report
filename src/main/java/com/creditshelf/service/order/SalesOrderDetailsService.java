package com.creditshelf.service.order;

import java.util.List;

import com.creditshelf.entity.SalesOrderDetails;

public interface SalesOrderDetailsService {
	List<SalesOrderDetails> saveAll(List<SalesOrderDetails> details);

}
