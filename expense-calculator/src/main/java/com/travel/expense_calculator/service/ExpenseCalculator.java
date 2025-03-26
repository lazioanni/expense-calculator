package com.travel.expense_calculator.service;


import com.travel.expense_calculator.enums.FuelType;
import com.travel.expense_calculator.enums.VehicleType;

import java.math.BigDecimal;

public interface ExpenseCalculator {
    /**
     * @param vehicleType               The type of selected vehicle (Car/Bus/Van etc.)
     * @param fuelType                  The type of selected fuel (Diesel/Petrol)
     * @param destination               The selected destination
     * @param numberOfPeopleTravelling  The number of people traveling
     * @param isAirConditioningRequired The selected option of air conditioning
     * @return BigDecimal The calculated expense
     */
    BigDecimal calculateExpense(VehicleType vehicleType, FuelType fuelType, String destination, Integer numberOfPeopleTravelling, Boolean isAirConditioningRequired);
}