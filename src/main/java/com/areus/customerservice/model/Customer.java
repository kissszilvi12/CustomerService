package com.areus.customerservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @NotNull
    @JsonProperty("id")
    private UUID id;

    @Column(length = 100)
    @NotBlank
    @JsonProperty("first_name")
    private String firstName;

    @Column(length = 100)
    @NotBlank
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank
    @JsonProperty("birth_name")
    private String birthName;

    @NotBlank
    @JsonProperty("mother_name")
    private String motherName;

    @NotNull
    @Past
    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(length = 100)
    @NotBlank
    @JsonProperty("nationality")
    private String nationality;

    @Column(length = 20)
    @NotBlank
    @JsonProperty("phone")
    private String phone;

    @NotBlank
    @JsonProperty("address")
    private String address;

    @Email
    @NotBlank
    @JsonProperty("email")
    private String email;
}