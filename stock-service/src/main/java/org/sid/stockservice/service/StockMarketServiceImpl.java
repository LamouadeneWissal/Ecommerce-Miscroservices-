package org.sid.stockservice.service;

import org.sid.stockservice.entity.StockMarket;
import org.sid.stockservice.feign.CompanyFeignClient;
import org.sid.stockservice.repository.StockMarketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockMarketServiceImpl implements StockMarketService {
    private final StockMarketRepository repository;
    private final CompanyFeignClient companyFeignClient;

    public StockMarketServiceImpl(StockMarketRepository repository, CompanyFeignClient companyFeignClient) {
        this.repository = repository;
        this.companyFeignClient = companyFeignClient;
    }

    @Override
    public StockMarket addStock(StockMarket stock) {
        StockMarket saved = repository.save(stock);
        List<StockMarket> latest = repository.findByCompanyIdOrderByDateDesc(saved.getCompanyId());
        if(!latest.isEmpty() && latest.get(0).getId().equals(saved.getId())) {
            companyFeignClient.updateCompanyPrice(saved.getCompanyId(), new CompanyFeignClient.UpdatePrice(saved.getCloseValue()));
        }
        return saved;
    }

    @Override
    public void deleteStock(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<StockMarket> allStocks() {
        return repository.findAll();
    }

    @Override
    public Optional<StockMarket> getStock(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<StockMarket> stocksByCompany(Long companyId) {
        return repository.findByCompanyIdOrderByDateDesc(companyId);
    }
}
