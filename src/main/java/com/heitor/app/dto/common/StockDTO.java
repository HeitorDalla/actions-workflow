package com.heitor.app.dto.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class StockDTO {
    @NotNull
    @Min(1)
    private Integer quantity;

    public StockDTO() {}

    public StockDTO(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
