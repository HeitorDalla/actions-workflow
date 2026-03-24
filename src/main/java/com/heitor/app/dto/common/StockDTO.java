package com.heitor.app.dto.common;

public class StockDTO {
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
