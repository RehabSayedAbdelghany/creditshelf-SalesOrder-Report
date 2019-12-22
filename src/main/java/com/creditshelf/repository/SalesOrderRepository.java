package com.creditshelf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.creditshelf.entity.RevenueProfit;
import com.creditshelf.entity.SalesOrder;

@Repository
public interface SalesOrderRepository  extends JpaRepository<SalesOrder, Long> {
	List<SalesOrder> findByCompany_name(String name);
	
	@Query("select New com.creditshelf.entity.RevenueProfit(c.name,month(so.orderDate), sum(sod.salePrice *0) ,sum(sod.salePrice * sod.quantity)  , sod.currencyCode,p.currency ) "
			+ "from com.creditshelf.entity.SalesOrder so join com.creditshelf.entity.SalesOrderDetails sod on so.orderId=sod.order.orderId "
			+ "join com.creditshelf.entity.Company c on c.id= so.company "
			+ "join com.creditshelf.entity.Product p on p.id=sod.product.id "
			+ "where year(so.orderDate)=year( CURRENT_DATE) and month(so.orderDate)< month( CURRENT_DATE) and c.name =  :name "
			+ "group by so.company, sod.currencyCode, month(so.orderDate)")
	List<RevenueProfit> findRevenuesByCompany(@Param("name") String name);
	
	
	@Query("select New com.creditshelf.entity.RevenueProfit(c.name,month(so.orderDate), sum(p.price * sod.quantity),sum(sod.salePrice * sod.quantity)  , sod.currencyCode,p.currency ) "
			+ "from com.creditshelf.entity.SalesOrder so join com.creditshelf.entity.SalesOrderDetails sod on so.orderId=sod.order.orderId "
			+ "join com.creditshelf.entity.Company c on c.id= so.company "
			+ "join com.creditshelf.entity.Product p on p.id=sod.product.id "
			+ "where year(so.orderDate)=year( CURRENT_DATE) and month(so.orderDate)< month( CURRENT_DATE) and c.name =  :name "
			+ "group by so.company, sod.currencyCode, month(so.orderDate)")
	List<RevenueProfit> findNetProfitByCompany(@Param("name") String name);

}
