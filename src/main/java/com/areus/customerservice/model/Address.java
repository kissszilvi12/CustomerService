package com.areus.customerservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Address {
    @Id
    private UUID id;

    @NotBlank
    private String country;

    @Column(length = 100)
    @NotBlank
    private String city;

    @Column(length = 10)
    @NotBlank
    private String zipCode;

    @Column(length = 100)
    @NotBlank
    private String address;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Customer customer;
}
