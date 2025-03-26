package com.travel.expense_calculator.controller;

import com.travel.expense_calculator.dto.ExpenseDTO;
import com.travel.expense_calculator.enums.FuelType;
import com.travel.expense_calculator.enums.VehicleType;
import com.travel.expense_calculator.service.ExpenseCalculator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

import static com.travel.expense_calculator.validator.ExpenseValidator.isValidDestinationAndDistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Controller class that handles HTTP requests.
 * This class also provides error responses for invalid inputs.
 *
 * @author lioannidis
 * @version 0.1
 */
@Controller
public class ExpenseController {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    private final ExpenseCalculator expenseCalculator;

    @Autowired
    public ExpenseController(ExpenseCalculator expenseCalculator) {
        this.expenseCalculator = expenseCalculator;
    }


    /**
     * This method handles HTTP GET requests to the root URL ("/") and initializes
     * an empty {@link ExpenseDTO} object to be used in the form.
     *
     * @param model the {@link Model} object used to pass attributes to the view
     * @return the index.html page
     */
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("expenseForm", new ExpenseDTO());
        return "index";
    }

    /**
     * This method processes HTTP POST requests to the "/calculate-expense" endpoint.
     * It validates the input form data and returns the calculated expense.
     *
     * @param expenseDTO the {@link ExpenseDTO} containing user input data for the calculation
     * @param result     the {@link BindingResult} for handling validation errors
     * @param model      the {@link Model} used to pass attributes to the view
     * @return the index.html page with the calculated expense
     */
    @PostMapping("/calculate-expense")
    public String calculateExpense(@Valid @ModelAttribute("expenseForm") ExpenseDTO expenseDTO,
                                   BindingResult result, Model model) {

        logger.info("Received expense calculation request: {}", expenseDTO);

        if (!isValidDestinationAndDistance(expenseDTO.getDestinationAndDistance())) {
            logger.warn("Invalid destination and distance format: {}", expenseDTO.getDestinationAndDistance());
            result.rejectValue("destinationAndDistance", "error.destinationAndDistance", "Invalid destination and distance format.");
        }

        if (result.hasErrors()) {
            logger.warn("Validation errors found: {}", result.getAllErrors());
            return "index";
        }

        BigDecimal totalExpense = expenseCalculator.calculateExpense(
                VehicleType.valueOf(expenseDTO.getVehicleType()),
                FuelType.valueOf(expenseDTO.getFuelType()),
                expenseDTO.getDestinationAndDistance(),
                expenseDTO.getNumberOfPeopleTravelling(),
                expenseDTO.getIsAirConditioningRequired()
        );

        logger.info("Calculated total expense: {}", totalExpense);
        model.addAttribute("calculatedExpense", totalExpense);

        return "index";
    }
}
