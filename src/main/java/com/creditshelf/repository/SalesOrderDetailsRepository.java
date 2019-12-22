package com.creditshelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditshelf.entity.SalesOrderDetails;

@Repository
public interface SalesOrderDetailsRepository  extends JpaRepository<SalesOrderDetails, Long> {

}
