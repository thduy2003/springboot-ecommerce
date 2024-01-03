package com.thduy2003.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="city")
    private String city;
    @Column(name="country")
    private String country;
    @Column(name="street")
    private String street;
    @Column(name="zip_code")
    private String zipCode;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;
}
