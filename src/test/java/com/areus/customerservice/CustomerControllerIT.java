package com.areus.customerservice;

import com.areus.customerservice.controller.CustomerController;
import com.areus.customerservice.model.Customer;
import com.areus.customerservice.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void whenValidCustomerCreated_thenRespondWithOk() throws Exception {
        Customer validCustomer = new Customer();
        validCustomer.setId(UUID.randomUUID());
        validCustomer.setFirstName("John");
        validCustomer.setLastName("Doe");
        validCustomer.setBirthName("John Doe");
        validCustomer.setMotherName("Jane Doe");
        validCustomer.setNationality("American");
        validCustomer.setPhone("123-456-7890");
        validCustomer.setAddress("123 Main St");
        validCustomer.setEmail("john.doe@example.com");
        validCustomer.setDateOfBirth(LocalDate.of(1990, 1, 1));

        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();

        String customerJson = mapper.writeValueAsString(validCustomer);

        System.out.println(customerJson);

        mockMvc.perform(post("/customers/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isOk());
    }

    @Test
    public void whenCreateCustomerWithInvalidData_thenRespondWithBadRequest() throws Exception {
        Customer invalidCustomer = new Customer();
        invalidCustomer.setId(UUID.randomUUID()); // Missing required fields like firstName, lastName, etc.

        ObjectMapper mapper = new ObjectMapper();
        String customerJson = mapper.writeValueAsString(invalidCustomer);

        mockMvc.perform(post("/customers/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenNoCustomersFound_thenRespondWith204() throws Exception {
        when(customerService.findBetweenAges()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/customers/age-between"))
                .andExpect(status().isNoContent());
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
                .andExpect(jsonPath("$[0].first_name").value("Jane"))
                .andExpect(jsonPath("$[0].last_name").value("Doe"));
    }

    @Test
    public void whenAverageAgeRequested_thenRespondWith200AndAverage() throws Exception {
        when(customerService.getAverageAge()).thenReturn(35.0);

        mockMvc.perform(get("/customers/average-age"))
                .andExpect(status().isOk())
                .andExpect(content().string("35.0"));
    }
}