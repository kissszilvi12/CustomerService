package com.areus.customerservice.service;

import com.areus.customerservice.model.Customer;
import com.areus.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    /**
     * Finds customers between the ages of 18 and 40 years.
     * It calculates the date for 18 and 40 years ago from the current date
     * and filters the customers based on their date of birth falling between these dates.
     */
    public List<Customer> findBetweenAges() {
        LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);    // Calculate date for 18 years ago
        LocalDate fortyYearsAgo = LocalDate.now().minusYears(40);       // Calculate date for 40 years ago
        return repository.findAll().stream()
                .filter(customer -> customer.getDateOfBirth().isBefore(eighteenYearsAgo))
                .filter(customer -> customer.getDateOfBirth().isAfter(fortyYearsAgo))
                .toList();
    }

    /**
     * Calculates the average age of all customers.
     * The calculation is performed via a custom query in the repository that averages
     * the years between the current date and each customer's date of birth.
     */
    public Double getAverageAge() {
        return repository.calculateAvgAge();
    }


}