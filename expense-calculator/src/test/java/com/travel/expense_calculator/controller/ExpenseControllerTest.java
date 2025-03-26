package com.travel.expense_calculator.controller;

import com.travel.expense_calculator.service.ExpenseCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;



import com.travel.expense_calculator.dto.ExpenseDTO;
import com.travel.expense_calculator.enums.FuelType;
import com.travel.expense_calculator.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseCalculator expenseCalculator;

    private ExpenseDTO expenseDTO;

    @BeforeEach
    void init() {
        expenseDTO = new ExpenseDTO();
        expenseDTO.setVehicleType("CAR");
        expenseDTO.setFuelType("PETROL");
        expenseDTO.setDestinationAndDistance("Paris-300");
        expenseDTO.setNumberOfPeopleTravelling(2);
        expenseDTO.setIsAirConditioningRequired(true);
    }

    @Test
    void testShowForm_ShouldReturnIndexPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("expenseForm"));
    }

    @Test
    void testCalculateExpense_ValidInput_ShouldReturnIndexWithExpense() throws Exception {
        when(expenseCalculator.calculateExpense(any(VehicleType.class), any(FuelType.class),
                any(String.class), any(Integer.class), any(Boolean.class)))
                .thenReturn(BigDecimal.valueOf(100.50));

        mockMvc.perform(MockMvcRequestBuilders.post("/calculate-expense")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("vehicleType", "CAR")
                        .param("fuelType", "PETROL")
                        .param("destinationAndDistance", "Paris-300")
                        .param("numberOfPeopleTravelling", "2")
                        .param("isAirConditioningRequired", "true"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("calculatedExpense"))
                .andExpect(MockMvcResultMatchers.model().attribute("calculatedExpense", BigDecimal.valueOf(100.50)));
    }

    @Test
    void testCalculateExpense_InvalidInput_ShouldReturnIndexWithErrors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/calculate-expense")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("vehicleType", "CAR")
                        .param("fuelType", "PETROL")
                        .param("destinationAndDistance", "")
                        .param("numberOfPeopleTravelling", "2")
                        .param("isAirConditioningRequired", "true"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("expenseForm", "destinationAndDistance"));
    }
}
