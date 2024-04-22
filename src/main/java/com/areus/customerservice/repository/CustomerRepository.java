package com.areus.customerservice.repository;

import com.areus.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Query("SELECT AVG(MONTHS_BETWEEN(current date, c.dateOfBirth)/12) FROM Customer c")
    Double calculateAvgAge();
}
