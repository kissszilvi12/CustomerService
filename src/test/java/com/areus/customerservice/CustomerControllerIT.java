package com.areus.customerservice;

import com.areus.customerservice.controller.CustomerController;
import com.areus.customerservice.model.Customer;
import com.areus.customerservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void whenNoCustomersFound_thenRespondWith204() throws Exception {
        when(customerService.findBetweenAges()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/customers/age-between"))
                .andExpect(status().isNoContent());  // Using HTTP 204 as a response for no content
    }

    @Test
    public void whenCustomersFound_thenRespondWith200AndData() throws Exception {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setDateOfBirth(LocalDate.of(1990, 1, 1));

        List<Customer> customers = List.of(customer);
        when(customerService.findBetweenAges()).thenReturn(customers);

        mockMvc.perform(get("/customers/age-between"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("Jane"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"));
    }

    @Test
    public void whenAverageAgeRequested_thenRespondWith200AndAverage() throws Exception {
        when(customerService.getAverageAge()).thenReturn(35.0);

        mockMvc.perform(get("/customers/average-age"))
                .andExpect(status().isOk())
                .andExpect(content().string("35.0"));
    }

    @Test
    public void whenServiceThrowsException_thenRespondWith500() throws Exception {
        when(customerService.findBetweenAges()).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(get("/customers/age-between"))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertInstanceOf(RuntimeException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals("Unexpected error", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}