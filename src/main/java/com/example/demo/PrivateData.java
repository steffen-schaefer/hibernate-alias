package com.example.demo;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class PrivateData {
    @Id
    @GeneratedValue
    private UUID id;

    @Basic
    private String dataName;

    @ManyToOne
    private Person person;

    public PrivateData() {
    }

    public PrivateData(Person person, String dataName) {
        this.person = person;
        this.dataName = dataName;
    }
}
