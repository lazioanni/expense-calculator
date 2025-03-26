package com.travel.expense_calculator.util;

import com.travel.expense_calculator.enums.FuelType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.travel.expense_calculator.util.ExpenseCalculatorUtils.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ExpenseCalculatorUtilsTest {

    @Test
    void test_getChargeFromFuel_whenFuelIsDiesel_thenSuccess() {
        final var kilometers = BigDecimal.valueOf(100);
        final var fuel = FuelType.DIESEL;
        final var expectedResult = BigDecimal.valueOf(15).setScale(2);

        final var result = getChargeFromFuel(kilometers,fuel);

        assertThat(result).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void test_getChargeFromFuel_whenFuelIsPetrol_thenSuccess() {
        final var kilometers = BigDecimal.valueOf(100);
        final var fuel = FuelType.PETROL;
        final var expectedResult = BigDecimal.valueOf(20).setScale(2);

        final var result = getChargeFromFuel(kilometers,fuel);

        assertThat(result).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void test_getChargeFromAirCondition_thenSuccess() {
        final var kilometers = BigDecimal.valueOf(100);
        final var expectedResult = BigDecimal.valueOf(10).setScale(1);

        final var result = getChargeFromAirCondition(kilometers);

        assertThat(result).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void test_getChargeFromExtraPassengers_whenFivePassengers_thenSuccess() {
        final var kilometers = BigDecimal.valueOf(100);
        final var expectedResult = BigDecimal.valueOf(25).setScale(2);

        final var result = getChargeFromExtraPassengers(kilometers,5);

        assertThat(result).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void test_extractDistance_whenTheFormIsRight_thenSuccess() {
        final var destination = "Munich: 584 KM";
        final var expectedResult = BigDecimal.valueOf(584);

        final var result = extractDistance(destination);

        assertThat(result).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    void test_extractDistance_whenTheFormIsNull_thenThrow_IllegalArgumentException() {
        assertThatThrownBy(() ->  extractDistance(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input string cannot be null or empty");
    }

    @Test
    void test_extractDistance_whenTheFormIsNotRight_thenThrow_IllegalArgumentException() {
        final var destination = "Munich: KM";

        assertThatThrownBy(() ->  extractDistance(destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No valid distance found in the input string");
    }
}