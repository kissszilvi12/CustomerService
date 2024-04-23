package com.areus.customerservice;

import com.areus.customerservice.model.Customer;
import com.areus.customerservice.repository.CustomerRepository;
import com.areus.customerservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceIT {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void whenNoCustomersMatchAgeRange_thenResultIsEmpty() {
        when(customerRepository.findAll()).thenReturn(Collections.emptyList());

        List<Customer> customers = customerService.findBetweenAges();
        assertThat(customers).isEmpty();
    }

    @Test
    public void whenCustomersAreOnBoundaryAges_thenShouldBeIncluded() {
        Customer youngCustomer = new Customer();
        youngCustomer.setId(UUID.randomUUID());
        youngCustomer.setFirstName("Young");
        youngCustomer.setLastName("Boundary");
        youngCustomer.setDateOfBirth(LocalDate.now().minusYears(18));

        Customer oldCustomer = new Customer();
        oldCustomer.setId(UUID.randomUUID());
        oldCustomer.setFirstName("Old");
        oldCustomer.setLastName("Boundary");
        oldCustomer.setDateOfBirth(LocalDate.now().minusYears(40));

        when(customerRepository.findAll()).thenReturn(List.of(youngCustomer, oldCustomer));

        List<Customer> customers = customerService.findBetweenAges();
        assertThat(customers).hasSize(2).extracting(Customer::getFirstName).containsExactlyInAnyOrder("Young", "Old");
    }
}
