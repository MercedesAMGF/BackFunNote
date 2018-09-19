package com.back.funnote.company;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
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

	private CompanyRepository companyRepository;
	private CompanySequenceService companySequenceService;
	private Company company;

	CompanyController(CompanyRepository companyRepository, CompanySequenceService companySequenceService) {
		this.companyRepository = companyRepository;
		this.companySequenceService = companySequenceService;
	}

	@PostMapping public Company addCompany(@RequestBody Company company) {
		int sequence = this.companySequenceService.getNextSequence();
		company.setSequence(sequence);
		return this.companyRepository.save(company);
	}

	@GetMapping	public List<Company> getCompanies() {
		return this.companyRepository.findAll();
	}

	@GetMapping("/{id}") public Company getCompany(@PathVariable("id") String id) {
		return this.companyRepository.findById(id).get();
	}

	@PutMapping public Company editCompany(@RequestBody Company company) {
		return this.companyRepository.save(company);
	}

	@DeleteMapping("/{id}")	public Long deleteCompany(@PathVariable("id") String id) {
        return this.companyRepository.deleteCompanyById(id);
	}

}