package com.travel.expense_calculator.util;

import com.travel.expense_calculator.enums.VehicleType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.travel.expense_calculator.util.DiscountUtils.applyDiscount;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DiscountUtilsTest {

    @Test
    void test_applyDiscount_whenVehicleIsBus_thenSuccess() {
        final var vehicle = VehicleType.BUS;
        final var price  = BigDecimal.valueOf(100);
        final var expectedPrice = BigDecimal.valueOf(98).setScale(2);

        final var result = applyDiscount(vehicle,price);

        assertThat(result).isNotNull().isEqualTo(expectedPrice);
    }
}