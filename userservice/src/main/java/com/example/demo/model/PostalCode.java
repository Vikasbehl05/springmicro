package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PostalCode {

    @Id
    @Column
    private Integer pincode;
    @ManyToOne
    @JoinColumn(name = "id")
    private State state;


    @Override
    public String toString() {
        return "Address{" +
                ", pincode=" + pincode +
                ", state=" + state +
                '}';
    }
}
