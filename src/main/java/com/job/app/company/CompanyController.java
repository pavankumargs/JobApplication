package com.job.app.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping
	public ResponseEntity<List<Company>> getAllCompanies() {
		List<Company> companies = companyService.getAllCompanies();
		return new ResponseEntity<>(companies, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable("id") Long id,@RequestBody Company company) {
		boolean isCompanyUpdated = companyService.updateCompany(id, company);
		if (isCompanyUpdated) {
			return new ResponseEntity<>("Company Updated Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Company Id Not Found ", HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<String> addCompany(@RequestBody Company company) {
		companyService.createCompany(company);
		return new ResponseEntity<>("Company Created Successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable("id") Long id){
		boolean isCompanyDeleted = companyService.deleteCompanyById(id);
		if(isCompanyDeleted) {
			return new ResponseEntity<>("Company Deleted Successfully",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("Company ID Not Found",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompany(@PathVariable("id") Long id){
		Company company = companyService.getCompanyById(id);
		if(company != null) {
			return new ResponseEntity<>(company,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
