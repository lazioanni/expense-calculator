package com.travel.expense_calculator.util;

import com.travel.expense_calculator.enums.FuelType;

import java.math.BigDecimal;

/**
 * Utility class for calculate expenses.
 *
 * @author lioannidis
 * @version 0.1
 */
public class ExpenseCalculatorUtils {
    private static final BigDecimal CAR_EXCEEDS_THE_MAX_PEOPLE_LIMIT_CHARGE = BigDecimal.valueOf(0.05);
    private static final BigDecimal AIR_CONDITION_CHARGE = BigDecimal.valueOf(0.10);

    /**
     * Calculates the charge for a trip based on kilometers and fuel type price.
     *
     * @param kilometers the {@link BigDecimal} the distance from destination in kilometers
     * @param fuelType   the {@link FuelType} is used for the trip
     * @return the price {@link BigDecimal}
     */
    public static BigDecimal getChargeFromFuel(BigDecimal kilometers, FuelType fuelType) {
        return kilometers.multiply(fuelType.getPrice());
    }

    /**
     * Calculates the extra charge for a trip with Air Condition.
     *
     * @param kilometers the {@link BigDecimal} the distance from destination in kilometers
     * @return the extra cost {@link BigDecimal}
     */
    public static BigDecimal getChargeFromAirCondition(BigDecimal kilometers) {
        return kilometers.multiply(AIR_CONDITION_CHARGE);
    }

    /**
     * Calculates the extra charge for a trip with Extra Passengers.
     *
     * @param kilometers         the {@link BigDecimal} the distance from destination in kilometers
     * @param numberOfPassengers the number of trip passengers
     * @return the extra cost {@link BigDecimal}
     */
    public static BigDecimal getChargeFromExtraPassengers(BigDecimal kilometers, int numberOfPassengers) {
        return kilometers
                .multiply(CAR_EXCEEDS_THE_MAX_PEOPLE_LIMIT_CHARGE)
                .multiply(BigDecimal.valueOf(numberOfPassengers));
    }

    /**
     * Extracts the distance from user input.
     *
     * @param input the user input which contains the distance in kilometers
     * @return the kilometers {@link BigDecimal}
     * @throws IllegalArgumentException if the input is null, empty, or does not contain a number.
     */
    public static BigDecimal extractDistance(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        String numericPart = input.replaceAll("[^0-9]", "").trim();

        if (numericPart.isEmpty()) {
            throw new IllegalArgumentException("No valid distance found in the input string");
        }

        return new BigDecimal(numericPart);
    }
}
