package org.sid.companyservice.service;

import org.sid.companyservice.entity.Company;
import org.sid.companyservice.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company createCompany(String name, LocalDate ipoDate, Double initialPrice, String domain) {
        Company c = new Company(name, ipoDate, initialPrice, domain);
        return companyRepository.save(c);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Optional<Company> getCompany(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public List<Company> getCompaniesByDomain(String domain) {
        return companyRepository.findByDomain(domain);
    }

    @Override
    public Company updatePrice(Long id, Double price) {
        Company company = companyRepository.findById(id).orElseThrow();
        company.setCurrentPrice(price);
        return companyRepository.save(company);
    }
}
