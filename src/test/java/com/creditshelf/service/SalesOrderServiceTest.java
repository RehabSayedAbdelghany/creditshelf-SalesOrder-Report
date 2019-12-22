package com.creditshelf.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.creditshelf.repository.CompanyRepository;
import com.creditshelf.service.company.impl.CompanyServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SalesOrderServiceTest {
	

	    @InjectMocks
	    CompanyServiceImpl companyService;
	    
	    @Mock
	    CompanyRepository companyRepository;

	    private final String companyName="Company 1";

	    @Test
	    public void test() {}
}
