package com.travel.expense_calculator.controller;

import com.travel.expense_calculator.service.ExpenseCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ExpenseCalculator expenseCalculator;

    @Test
    void testShowForm_ShouldReturnIndexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("expenseForm"));
    }

    @Test
    void testCalculateExpense_ValidInput_ShouldReturnIndexWithExpense() throws Exception {
        when(expenseCalculator.calculateExpense(any(), any(),
                anyString(), any(), anyBoolean()))
                .thenReturn(BigDecimal.valueOf(100));

        mockMvc.perform(post("/calculate-expense")
                        .param("vehicleType", "CAR")
                        .param("fuelType", "PETROL")
                        .param("destinationAndDistance", "Munich: 584 KM")
                        .param("numberOfPeopleTravelling", "2")
                        .param("isAirConditioningRequired", "true"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("calculatedExpense"));
    }

    @Test
    void testCalculateExpense_InvalidInput_ShouldReturnIndexWithErrors() throws Exception {
        mockMvc.perform(post("/calculate-expense")
                        .param("vehicleType", "CAR")
                        .param("fuelType", "PETROL")
                        .param("destinationAndDistance", "")
                        .param("numberOfPeopleTravelling", "2")
                        .param("isAirConditioningRequired", "true"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("expenseForm", "destinationAndDistance"));
    }
}
