package com.areus.customerservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Document {
    @Id
    @NotBlank
    private UUID id;

    @Column(length = 100)
    @NotBlank
    private String type;

    @Column(length = 15)
    @NotBlank
    private String number;

    @NotBlank
    @FutureOrPresent
    private LocalDate expirationDate;
}
