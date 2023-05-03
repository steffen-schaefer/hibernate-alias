package com.example.demo;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private UUID id;

    @Basic
    private String userName;

    public Person() {
    }

    public Person(String userName) {
        this.userName = userName;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String thingName) {
        this.userName = thingName;
    }
}
