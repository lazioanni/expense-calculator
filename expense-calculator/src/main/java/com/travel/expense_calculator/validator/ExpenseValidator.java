package com.travel.expense_calculator.validator;

import static com.travel.expense_calculator.util.ExpenseCalculatorUtils.extractDistance;

/**
 * Class for validation.
 *
 * @author lioannidis
 * @version 0.1
 */
public class ExpenseValidator {

    /**
     * Validates the user input.
     *
     * @param input the user input
     * @return true if the input is valid, otherwise false
     */
    public static boolean isValidDestinationAndDistance(String input) {
        try {
            extractDistance(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
