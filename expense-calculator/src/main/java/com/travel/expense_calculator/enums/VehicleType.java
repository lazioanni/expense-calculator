package com.travel.expense_calculator.enums;

import lombok.Getter;

/**
 * FuelType is an enum that represents the vehicle types
 * supported and the respective max Passengers.
 *
 * @author lioannidis
 * @version 0.1
 */
public enum VehicleType {
    CAR(0.00, 5),
    BUS(0.02, 50),
    VAN(0.00, 8),
    SUV(0.00, 7);

    @Getter
    private final double discount;

    @Getter
    private final int maxPassengers;

    VehicleType(double discount, int maxPassengers) {
        this.discount = discount;
        this.maxPassengers = maxPassengers;
    }
}