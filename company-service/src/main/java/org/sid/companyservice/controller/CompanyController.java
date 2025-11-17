package org.sid.companyservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sid.companyservice.dto.CompanyRequest;
import org.sid.companyservice.dto.UpdatePriceRequest;
import org.sid.companyservice.entity.Company;
import org.sid.companyservice.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> create(@Valid @RequestBody CompanyRequest request) {
        Company c = companyService.createCompany(request.getName(), request.getIpoDate(), request.getInitialPrice(), request.getDomain());
        return ResponseEntity.ok(c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<Company> updatePrice(@PathVariable Long id, @Valid @RequestBody UpdatePriceRequest req) {
        Company c = companyService.updatePrice(id, req.getPrice());
        return ResponseEntity.ok(c);
    }

    @GetMapping
    public List<Company> all() { return companyService.getAllCompanies(); }

    @GetMapping("/{id}")
    public ResponseEntity<Company> get(@PathVariable Long id) {
        return companyService.getCompany(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/domain/{domain}")
    public List<Company> byDomain(@PathVariable String domain) { return companyService.getCompaniesByDomain(domain); }
}
