package com.travel.expense_calculator.validator;

import org.junit.jupiter.api.Test;

import static com.travel.expense_calculator.validator.ExpenseValidator.isValidDestinationAndDistance;
import static org.junit.jupiter.api.Assertions.*;

class ExpenseValidatorTest {

    @Test
    void test_isValidDestinationAndDistance_thenReturnTrue() {
        final var validDestination =  "Munich: 584 KM";
        assertTrue(isValidDestinationAndDistance(validDestination));
    }

    @Test
    void test_isValidDestinationAndDistance_thenReturnFalse() {
        final var validDestination =  "Munich: KM";
        assertFalse(isValidDestinationAndDistance(validDestination));
    }

    @Test
    void test_isValidDestinationAndDistanceWithNull_thenReturnFalse() {
        assertFalse(isValidDestinationAndDistance(null));
    }
}