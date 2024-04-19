package com.areus.customerservice.service;

import com.areus.customerservice.model.Customer;
import com.areus.customerservice.repository.CustomerRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;


    public List<Customer> findBetweenAges() {
        @NotNull @Past LocalDate from = LocalDate.now(); // TODO: calculate 18 years ago
        @NotNull @Past LocalDate to = LocalDate.now(); // TODO: calculate 40 years ago

        return repository.findByDateOfBirthBetween(from, to);
    }
}
