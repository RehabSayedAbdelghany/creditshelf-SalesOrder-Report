package com.creditshelf.service.company;


import java.util.Optional;

import com.creditshelf.entity.Company;

public interface CompanyService {

	Company getCompanyOrCreate(String name);

	Optional<Company> findByName(String name);

}
