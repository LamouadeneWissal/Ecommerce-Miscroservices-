package org.sid.aichatbot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@FeignClient(name = "company-service")
public interface CompanyApi {
    @PostMapping("/companies")
    Map<String, Object> create(@RequestBody CreateCompany req);

    @GetMapping("/companies/{id}")
    Map<String, Object> get(@PathVariable("id") Long id);

    @PutMapping("/companies/{id}/price")
    Map<String, Object> updatePrice(@PathVariable("id") Long id, @RequestBody UpdatePrice req);

    @GetMapping("/companies")
    Object list();

    @GetMapping("/companies/domain/{domain}")
    Object listByDomain(@PathVariable("domain") String domain);

    record CreateCompany(String name, LocalDate ipoDate, Double initialPrice, String domain) {}
    record UpdatePrice(Double price) {}
}
