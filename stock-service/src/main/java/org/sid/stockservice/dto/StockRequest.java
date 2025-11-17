package org.sid.stockservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockRequest {
    private LocalDateTime date;
    @NotNull
    private Double openValue;
    @NotNull
    private Double highValue;
    @NotNull
    private Double lowValue;
    @NotNull
    private Double closeValue;
    @NotNull
    @Positive
    private Long volume;
    @NotNull
    private Long companyId;
}
