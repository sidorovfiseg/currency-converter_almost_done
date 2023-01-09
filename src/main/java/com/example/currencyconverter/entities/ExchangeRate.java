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

    public ExchangeRate(Currency currency, Double value) {
        this.currency = currency;
        this.value = value;
    }
}
