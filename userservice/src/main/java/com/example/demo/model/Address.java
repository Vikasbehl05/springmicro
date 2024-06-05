package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue
    private int addressId;
    @Column
    private String addressline;
    @Column
    private String streetNumber;
    @Column
    private String city;
    @Column
    private String phoneNumber;
    @ManyToOne
    @Transient
    private State state;
    @ManyToOne
    @Transient
    private PostalCode postalCode;
}
