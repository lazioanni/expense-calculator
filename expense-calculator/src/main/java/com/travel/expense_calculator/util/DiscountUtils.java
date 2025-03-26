package com.travel.expense_calculator.util;

import com.travel.expense_calculator.enums.VehicleType;

import java.math.BigDecimal;

/**
 * Utility class for discount.
 *
 * @author lioannidis
 * @version 0.1
 */
public class DiscountUtils {

    /**
     * Applies a discount to the given price based on the vehicle type.
     *
     * @param vehicleType the {@link VehicleType} for which the discount is determined
     * @param price       the original {@link BigDecimal} price before applying the discount
     * @return the discounted {@link BigDecimal} price after applying the reduction
     */
    public static BigDecimal applyDiscount(VehicleType vehicleType, BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(1 - vehicleType.getDiscount()));
    }
}
