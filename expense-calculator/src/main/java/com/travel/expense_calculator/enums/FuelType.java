package com.travel.expense_calculator.enums;

import lombok.Getter;

import java.math.BigDecimal;


/**
 * FuelType is an enum that represents the fuel types
 * supported and the respective prices/km.
 *
 * @author lioannidis
 * @version 0.1
 */
public enum FuelType {
    DIESEL(new BigDecimal("0.15")),
    PETROL(new BigDecimal("0.20"));

    @Getter
    private final BigDecimal price;

    FuelType(BigDecimal price) {
        this.price = price;
    }
}