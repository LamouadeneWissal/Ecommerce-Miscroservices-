package org.sid.companyservice.service;

import lombok.RequiredArgsConstructor;
import org.sid.companyservice.entity.Company;
import org.sid.companyservice.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public Company createCompany(String name, LocalDate ipoDate, Double initialPrice, String domain) {
        Company c = Company.builder()
                .name(name)
                .ipoDate(ipoDate)
                .currentPrice(initialPrice)
                .domain(domain)
                .build();
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
