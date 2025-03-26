package com.travel.expense_calculator.service;

import com.travel.expense_calculator.enums.FuelType;
import com.travel.expense_calculator.enums.VehicleType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.travel.expense_calculator.util.DiscountUtils.applyDiscount;
import static com.travel.expense_calculator.util.ExpenseCalculatorUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ExpenseCalculatorImpl is a service that
 * implements the business logic of {@link ExpenseCalculator} interface.
 *
 * @author lioannidis
 * @version 0.1
 */
@Service
public class ExpenseCalculatorImpl implements ExpenseCalculator {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseCalculatorImpl.class);

    /**
     * Calculates the total travel expense based on parameters.
     *
     * @param vehicleType               The type of selected vehicle (Car/Bus/Van etc.)
     * @param fuelType                  The type of selected fuel (Diesel/Petrol)
     * @param destination               The selected destination
     * @param numberOfPeopleTravelling  The number of people traveling
     * @param isAirConditioningRequired The selected option of air conditioning
     * @return BigDecimal The calculated expense
     */
    @Override
    public BigDecimal calculateExpense(VehicleType vehicleType,
                                       FuelType fuelType,
                                       String destination,
                                       Integer numberOfPeopleTravelling,
                                       Boolean isAirConditioningRequired) {

        final var kilometers = extractDistance(destination);
        logger.info("Extracted distance: {} km", kilometers);

        var price = getChargeFromFuel(kilometers, fuelType);
        logger.info("Initial price based on fuel charge: {}", price);

        if (isAirConditioningRequired) {
            BigDecimal airConditionCharge = getChargeFromAirCondition(kilometers);
            price = price.add(airConditionCharge);
            logger.info("Added Air Condition charge: {}, New price: {}", airConditionCharge, price);
        }

        if (numberOfPeopleTravelling > vehicleType.getMaxPassengers()) {
            BigDecimal extraPassengerCharge = getChargeFromExtraPassengers(kilometers, numberOfPeopleTravelling);
            price = price.add(extraPassengerCharge);
            logger.info("Added extra passenger charge: {}, New price: {}", extraPassengerCharge, price);
        }

        if (vehicleType == VehicleType.BUS) {
            price = applyDiscount(vehicleType, price);
            logger.info("Apply discount: {}", price);
        }

        return price;
    }
}
