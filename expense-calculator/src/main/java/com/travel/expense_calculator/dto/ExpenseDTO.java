package com.travel.expense_calculator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ExpenseDTO is a DTO that represents
 * the user input in our form and used to
 * transfer data from Controller layer to Service layer.
 *
 * @author lioannidis
 * @version 0.1
 */
@Data
@NoArgsConstructor
public class ExpenseDTO {
    private String vehicleType;
    private String fuelType;

    @NotBlank(message = "Destination and Distance are required")
    private String destinationAndDistance;

    @NotNull(message = "Number of People is required")
    @Positive(message = "Number of People must be a positive number")
    private Integer numberOfPeopleTravelling;

    private Boolean isAirConditioningRequired;
}