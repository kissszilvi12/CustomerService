package com.areus.customerservice.controller;

import com.areus.customerservice.model.Customer;
import com.areus.customerservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @PostMapping(value = "/new")
    @Operation(summary = "Create a new customer",
            description = "Registers a new customer in the system. The method expects a complete and valid customer object. ")
    ResponseEntity<Void> create(@Valid @RequestBody Customer customer) {
        service.create(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/age-between")
    @Operation(summary = "Customers aged between 18 and 40 years",
            description = "Finds customers between the ages of 18 and 40 years.")
    public ResponseEntity<List<Customer>> getCustomersBetweenAge() {
        List<Customer> customers = service.findBetweenAges();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/average-age")
    @Operation(summary = "Get the average age",
            description = "Calculates and returns the average age of all customers.")
    public ResponseEntity<Double> getAvgAge() {
        Double avgAge = service.getAverageAge();
        if (avgAge == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(avgAge);
    }
}
