package org.sid.companyservice.repository;

import org.sid.companyservice.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByDomain(String domain);
}
