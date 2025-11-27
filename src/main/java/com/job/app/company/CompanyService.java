package com.job.app.company;

import java.util.List;

public interface CompanyService {
	
	public List<Company> getAllCompanies();
	
	public boolean updateCompany(Long id, Company company);
	
	public void createCompany(Company company);
	
	public boolean deleteCompanyById(Long id);
	
	public Company getCompanyById(Long id);
	
}
