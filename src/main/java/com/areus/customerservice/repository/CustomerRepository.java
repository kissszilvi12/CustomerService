package com.areus.customerservice.repository;

import com.areus.customerservice.model.Customer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findByDateOfBirthBetween(@NotNull @Past LocalDate dateOfBirth, @NotNull @Past LocalDate dateOfBirth2);
}
