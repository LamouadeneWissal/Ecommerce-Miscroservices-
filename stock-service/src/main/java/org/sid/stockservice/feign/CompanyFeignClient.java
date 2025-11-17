package org.sid.stockservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "company-service")
public interface CompanyFeignClient {
    @PutMapping("/companies/{id}/price")
    void updateCompanyPrice(@PathVariable("id") Long id, @RequestBody UpdatePrice updatePrice);

    class UpdatePrice { private Double price; public UpdatePrice() {} public UpdatePrice(Double price){this.price=price;} public Double getPrice(){return price;} public void setPrice(Double price){this.price=price;} }
}
