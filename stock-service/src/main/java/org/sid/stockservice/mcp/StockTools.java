package org.sid.stockservice.mcp;

import org.sid.stockservice.dto.StockRequest;
import org.sid.stockservice.entity.StockMarket;
import org.sid.stockservice.service.StockMarketService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Placeholder MCP tools exposing Stock operations to an AI agent.
 */
@Component
public class StockTools {
    private final StockMarketService service;

    public StockTools(StockMarketService service) {
        this.service = service;
    }

    public StockMarket addStock(StockRequest request) {
        StockMarket stock = new StockMarket(
                request.getDate()!=null?request.getDate(): LocalDateTime.now(),
                request.getOpenValue(),
                request.getHighValue(),
                request.getLowValue(),
                request.getCloseValue(),
                request.getVolume(),
                request.getCompanyId()
        );
        return service.addStock(stock);
    }

    public void deleteStock(Long id) {
        service.deleteStock(id);
    }

    public List<StockMarket> listStocks() {
        return service.allStocks();
    }

    public Optional<StockMarket> getStock(Long id) {
        return service.getStock(id);
    }

    public List<StockMarket> listStocksByCompany(Long companyId) {
        return service.stocksByCompany(companyId);
    }
}
