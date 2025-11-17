package org.sid.stockservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sid.stockservice.dto.StockRequest;
import org.sid.stockservice.entity.StockMarket;
import org.sid.stockservice.service.StockMarketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockMarketController {
    private final StockMarketService service;

    @PostMapping
    public ResponseEntity<StockMarket> create(@Valid @RequestBody StockRequest request) {
        StockMarket stock = StockMarket.builder()
                .date(request.getDate() != null ? request.getDate() : LocalDateTime.now())
                .openValue(request.getOpenValue())
                .highValue(request.getHighValue())
                .lowValue(request.getLowValue())
                .closeValue(request.getCloseValue())
                .volume(request.getVolume())
                .companyId(request.getCompanyId())
                .build();
        return ResponseEntity.ok(service.addStock(stock));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<StockMarket> all() { return service.allStocks(); }

    @GetMapping("/{id}")
    public ResponseEntity<StockMarket> get(@PathVariable Long id) {
        return service.getStock(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/company/{companyId}")
    public List<StockMarket> byCompany(@PathVariable Long companyId) { return service.stocksByCompany(companyId); }
}
