package com.example.currencyconverter.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

/*
In this class we are creating Exchange rate
witch stores our currency and his price according to nominal
count of rubbles
 */

@Entity
@Table(name="exchange_rates")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Integer id;

    @OneToOne
    private Currency currency;

    private Double value;

    private LocalDate date = LocalDate.now();

    public ExchangeRate() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ExchangeRate(Currency currency, Double value) {
        this.currency = currency;
        this.value = value;
    }
}
