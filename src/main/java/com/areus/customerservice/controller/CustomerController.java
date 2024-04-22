package com.areus.customerservice.controller;

import com.areus.customerservice.model.Customer;
import com.areus.customerservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/age-between")
    @Operation(summary = "Customers aged between 18 and 40 years",
            description = "Finds customers between the ages of 18 and 40 years.")
    public List<Customer> getCustomersBetweenAge() {
        return service.findBetweenAges();
    }

    @GetMapping("/average-age")
    @Operation(summary = "Get the average age",
            description = "Calculates and returns the average age of all customers.")
    public Double getAvgAge() {
        return service.getAverageAge();
    }
}