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

    public List<Customer> findBetweenAges() {
        LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);    //2006
        LocalDate fortyYearsAgo = LocalDate.now().minusYears(40);       //1984
        return repository.findAll().stream()
                .filter(customer -> customer.getDateOfBirth().isBefore(eighteenYearsAgo))
                .filter(customer -> customer.getDateOfBirth().isAfter(fortyYearsAgo))
                .toList();
    }

    public Double getAverageAge() {
        return repository.calculateAvgAge();
    }


}