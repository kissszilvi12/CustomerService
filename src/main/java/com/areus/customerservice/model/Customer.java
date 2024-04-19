package com.areus.customerservice.model;

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
    private UUID id;

    @Column(length = 100)
    @NotNull @NotBlank
    private String firstName;

    @Column(length = 100)
    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    private String birthName;

    @NotNull
    @NotBlank
    private String motherName;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @Column(length = 100)
    @NotNull
    @NotBlank
    private String nationality;

    @Column(length = 20)
    @NotNull
    @NotBlank
    private String phone;

    @Email
    @NotNull
    @NotBlank
    private String email;
}
