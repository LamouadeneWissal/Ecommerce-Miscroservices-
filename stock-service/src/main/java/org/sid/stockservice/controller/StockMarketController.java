package org.sid.stockservice.controller;

import org.sid.stockservice.dto.StockRequest;
import org.sid.stockservice.entity.StockMarket;
import org.sid.stockservice.service.StockMarketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockMarketController {
    private final StockMarketService service;

    public StockMarketController(StockMarketService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StockMarket> create(@RequestBody StockRequest request) {
        StockMarket stock = new StockMarket(
                request.getDate()!=null?request.getDate(): LocalDateTime.now(),
                request.getOpenValue(),
                request.getHighValue(),
                request.getLowValue(),
                request.getCloseValue(),
                request.getVolume(),
                request.getCompanyId()
        );
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
