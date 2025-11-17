package org.sid.aichatbot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@FeignClient(name = "stock-service")
public interface StockApi {
    @PostMapping("/stocks")
    Map<String, Object> create(@RequestBody CreateStock req);

    @GetMapping("/stocks/{id}")
    Map<String, Object> get(@PathVariable("id") Long id);

    @GetMapping("/stocks/company/{companyId}")
    Object byCompany(@PathVariable("companyId") Long companyId);

    @DeleteMapping("/stocks/{id}")
    void delete(@PathVariable("id") Long id);

    record CreateStock(LocalDateTime date, Double openValue, Double highValue, Double lowValue, Double closeValue, Long volume, Long companyId) {}
}
