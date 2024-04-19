package com.areus.customerservice.controller;

import com.areus.customerservice.model.Customer;
import com.areus.customerservice.service.CustomerService;
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
    public List<Customer> getCustomersBetweenAge() {
        return service.findBetweenAges();
    }
}
