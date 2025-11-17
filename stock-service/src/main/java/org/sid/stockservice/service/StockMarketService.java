package org.sid.stockservice.service;

import org.sid.stockservice.entity.StockMarket;
import java.util.List;
import java.util.Optional;

public interface StockMarketService {
    StockMarket addStock(StockMarket stock);
    void deleteStock(Long id);
    List<StockMarket> allStocks();
    Optional<StockMarket> getStock(Long id);
    List<StockMarket> stocksByCompany(Long companyId);
}
