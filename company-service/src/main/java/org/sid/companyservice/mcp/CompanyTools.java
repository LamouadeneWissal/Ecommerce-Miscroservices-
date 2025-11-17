package org.sid.companyservice.mcp;

import org.sid.companyservice.dto.CompanyRequest;
import org.sid.companyservice.dto.UpdatePriceRequest;
import org.sid.companyservice.entity.Company;
import org.sid.companyservice.service.CompanyService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Placeholder MCP tools exposing Company operations to an AI agent.
 */
@Component
public class CompanyTools {
    private final CompanyService companyService;

    public CompanyTools(CompanyService companyService) {
        this.companyService = companyService;
    }

    public Company registerCompany(CompanyRequest request) {
        return companyService.createCompany(request.getName(), request.getIpoDate(), request.getInitialPrice(), request.getDomain());
    }

    public void deleteCompany(Long id) {
        companyService.deleteCompany(id);
    }

    public Company updateCompanyPrice(Long id, UpdatePriceRequest req) {
        return companyService.updatePrice(id, req.getPrice());
    }

    public List<Company> listCompanies() {
        return companyService.getAllCompanies();
    }

    public Optional<Company> getCompany(Long id) {
        return companyService.getCompany(id);
    }

    public List<Company> listCompaniesByDomain(String domain) {
        return companyService.getCompaniesByDomain(domain);
    }
}
