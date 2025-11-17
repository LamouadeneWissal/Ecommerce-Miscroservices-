package org.sid.companyservice.dto;

import java.time.LocalDate;

public class CompanyRequest {
    private String name;
    private LocalDate ipoDate;
    private Double initialPrice;
    private String domain;

    public CompanyRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getIpoDate() { return ipoDate; }
    public void setIpoDate(LocalDate ipoDate) { this.ipoDate = ipoDate; }
    public Double getInitialPrice() { return initialPrice; }
    public void setInitialPrice(Double initialPrice) { this.initialPrice = initialPrice; }
    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
}
