package com.example.currencyconverter.entities;

import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "users_requests")
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String char_code;

    private String name;

    private Integer nominal;

    private Double value;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChar_code() {
        return char_code;
    }

    public void setChar_code(String char_code) {
        this.char_code = char_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public UserRequest() {
    }

    public UserRequest(String char_code, String name, Integer nominal, Double value, User user) {
        this.char_code = char_code;
        this.name = name;
        this.nominal = nominal;
        this.value = value;
        this.user = user;
    }


}
