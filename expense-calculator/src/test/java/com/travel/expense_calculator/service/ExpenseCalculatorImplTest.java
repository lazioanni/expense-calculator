package com.travel.expense_calculator.service;

import com.travel.expense_calculator.enums.FuelType;
import com.travel.expense_calculator.enums.VehicleType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ExpenseCalculatorImplTest {

    private final ExpenseCalculator travelExpenseService = new ExpenseCalculatorImpl();


    @Test
    void test_calculateExpense_forMunich_thenSuccess() {
        final var vehicleType = VehicleType.CAR;
        final var fuelType = FuelType.PETROL;
        final var destination = "Munich: 584 KM";
        final var numberOfPeopleTravelling = 5;
        final var isAirConditioningRequired = true;
        final var expectedResult = BigDecimal.valueOf(175.2).setScale(2);

        final var result = travelExpenseService.calculateExpense(
                vehicleType,
                fuelType,
                destination,
                numberOfPeopleTravelling,
                isAirConditioningRequired
        );

        assertThat(result).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void test_calculateExpense_forMunichAndApplyDiscount_thenSuccess() {
        final var vehicleType = VehicleType.BUS;
        final var fuelType = FuelType.PETROL;
        final var destination = "Munich: 584 KM";
        final var numberOfPeopleTravelling = 5;
        final var isAirConditioningRequired = true;
        final var expectedResult = BigDecimal.valueOf(171.6960).setScale(4);

        final var result = travelExpenseService.calculateExpense(
                vehicleType,
                fuelType,
                destination,
                numberOfPeopleTravelling,
                isAirConditioningRequired
        );

        assertThat(result).isNotNull().isEqualTo(expectedResult);
    }
}