package com.job.app.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<Company> getAllCompanies() {
		List<Company> companies = companyRepository.findAll();
		return companies; 
	}

	@Override
	public boolean updateCompany(Long id, Company company) {
		Optional<Company> companyOptioanl = companyRepository.findById(id);
		if (companyOptioanl.isPresent()) {
			Company updateCompany = companyOptioanl.get();
			updateCompany.setName(company.getName());
			updateCompany.setDescription(company.getDescription());
			updateCompany.setJobs(company.getJobs());
			
			companyRepository.save(updateCompany);
			return true;
		}
		return false;
	}

	@Override
	public void createCompany(Company company) {
		companyRepository.save(company);
	}

	@Override
	public boolean deleteCompanyById(Long id) {
		if(companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Company getCompanyById(Long id) {
		return companyRepository.findById(id).orElse(null); //if the company id is found will return otherwise it returns null
	}

}
