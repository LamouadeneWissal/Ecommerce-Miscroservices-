package org.sid.stockservice.repository;

import org.sid.stockservice.entity.StockMarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockMarketRepository extends JpaRepository<StockMarket, Long> {
    List<StockMarket> findByCompanyIdOrderByDateDesc(Long companyId);
}
