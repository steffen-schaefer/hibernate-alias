package com.example.demo;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class PrivateSubData {
    @Id
    @GeneratedValue
    private UUID id;

    @Basic
    private String dataName;

    @ManyToOne
    private PrivateData data;

    public PrivateSubData() {
    }

    public PrivateSubData(PrivateData data, String dataName) {
        this.data = data;
        this.dataName = dataName;
    }
}
