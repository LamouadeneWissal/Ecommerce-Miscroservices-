package org.sid.stockservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StockMarket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private Double openValue;
    private Double highValue;
    private Double lowValue;
    private Double closeValue;
    private Long volume;
    private Long companyId;

    public StockMarket() {}

    public StockMarket(LocalDateTime date, Double openValue, Double highValue, Double lowValue, Double closeValue, Long volume, Long companyId) {
        this.date = date;
        this.openValue = openValue;
        this.highValue = highValue;
        this.lowValue = lowValue;
        this.closeValue = closeValue;
        this.volume = volume;
        this.companyId = companyId;
    }

    public Long getId() { return id; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public Double getOpenValue() { return openValue; }
    public void setOpenValue(Double openValue) { this.openValue = openValue; }
    public Double getHighValue() { return highValue; }
    public void setHighValue(Double highValue) { this.highValue = highValue; }
    public Double getLowValue() { return lowValue; }
    public void setLowValue(Double lowValue) { this.lowValue = lowValue; }
    public Double getCloseValue() { return closeValue; }
    public void setCloseValue(Double closeValue) { this.closeValue = closeValue; }
    public Long getVolume() { return volume; }
    public void setVolume(Long volume) { this.volume = volume; }
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
}
