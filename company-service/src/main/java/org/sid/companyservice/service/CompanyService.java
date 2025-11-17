package org.sid.companyservice.service;

import org.sid.companyservice.entity.Company;
import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company createCompany(String name, java.time.LocalDate ipoDate, Double initialPrice, String domain);
    void deleteCompany(Long id);
    Optional<Company> getCompany(Long id);
    List<Company> getAllCompanies();
    List<Company> getCompaniesByDomain(String domain);
    Company updatePrice(Long id, Double price);
}
