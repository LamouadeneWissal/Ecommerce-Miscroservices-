package org.sid.stockservice.dto;

import java.time.LocalDateTime;

public class StockRequest {
    private LocalDateTime date;
    private Double openValue;
    private Double highValue;
    private Double lowValue;
    private Double closeValue;
    private Long volume;
    private Long companyId;

    public StockRequest() {}

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
