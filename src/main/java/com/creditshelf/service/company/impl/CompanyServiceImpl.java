package com.creditshelf.service.company.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.creditshelf.entity.Company;
import com.creditshelf.repository.CompanyRepository;
import com.creditshelf.service.company.CompanyService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired CompanyRepository companyRepository;
	
	@Override
	public Company getCompanyOrCreate(String name) {
		Optional<Company> company = companyRepository.findByName(name);
		log.info("Is company found--> crete new one if not found  ",company.isPresent());
        return company.orElseGet(() ->(companyRepository.save(mapCompanyModel(name))));
	}
	
	private Company mapCompanyModel(String name) {
		Company company = new Company();
		company.setName(name);
		return company;
	}
	
	@Override
	public Optional<Company> findByName(String name) {
		log.info("getting the company by name {}  ",name);
		return companyRepository.findByName(name);
	
	}
}
