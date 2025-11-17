package org.sid.companyservice.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate ipoDate;
    private Double currentPrice;
    private String domain;

    public Company() {}

    public Company(String name, LocalDate ipoDate, Double currentPrice, String domain) {
        this.name = name;
        this.ipoDate = ipoDate;
        this.currentPrice = currentPrice;
        this.domain = domain;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getIpoDate() { return ipoDate; }
    public void setIpoDate(LocalDate ipoDate) { this.ipoDate = ipoDate; }
    public Double getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(Double currentPrice) { this.currentPrice = currentPrice; }
    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
}
